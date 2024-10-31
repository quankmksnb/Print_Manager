package com.example.print_manager_final.service;

import com.example.print_manager_final.dto.request.AuthenticationRequest;
import com.example.print_manager_final.dto.request.IntrospectRequest;
import com.example.print_manager_final.dto.request.LogoutRequest;
import com.example.print_manager_final.dto.request.RefreshTokenRequest;
import com.example.print_manager_final.dto.response.AuthenticationResponse;
import com.example.print_manager_final.dto.response.IntrospectResponse;
import com.example.print_manager_final.exception.AppException;
import com.example.print_manager_final.exception.ErrorException;
import com.example.print_manager_final.model.RefreshToken;
import com.example.print_manager_final.model.User;
import com.example.print_manager_final.repository.RefreshTokenRepository;
import com.example.print_manager_final.repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AuthenticationService {
    UserRepository userRepository;
    RefreshTokenRepository refreshTokenRepository;


    @NonFinal   // đánh dấu không cho nó inject vào constructor
    @Value("${jwt.keySigner}")      // đọc biến từ file properties
    protected String KEY_SINGER;

    @NonFinal
    @Value("${jwt.valid-duration}")
    protected long VALID_DURATION;

    @NonFinal
    @Value("${jwt.refreshable-duration}")
    protected long REFRESHABLE_DURATION;

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        var user = userRepository.findByUserName(authenticationRequest.getUsername())
                .orElseThrow(() -> new AppException(ErrorException.USER_NO_EXISTED));
        // check password được đưa vào so với trong db
        boolean checkPassword = passwordEncoder.matches(authenticationRequest.getPassword()
                , user.getPassword());

        if (!checkPassword) {
            throw new AppException(ErrorException.INCORECT_PASSWORD);
        }
        var token = createToken(user);

        return AuthenticationResponse.builder()
                .token(token)
                .check(true)
                .build();
    }

    public void logout(LogoutRequest logoutRequest) throws ParseException, JOSEException {
        try {
            var signToken = verifyToken(logoutRequest.getToken(), true);
            String userName = signToken.getJWTClaimsSet().getSubject();
            Date expiryTime = signToken.getJWTClaimsSet().getExpirationTime();

            RefreshToken refreshToken = RefreshToken.builder()
                    .user(userRepository.findByUserName(userName).get())
                    .token(logoutRequest.getToken())
                    .expiryTime(expiryTime)
                    .build();

            refreshTokenRepository.save(refreshToken);
        } catch (Exception e) {
            log.info("Token already expired");
        }

    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest refreshToken) throws ParseException, JOSEException {
        var signToken = verifyToken(refreshToken.getToken(), true);
        String userName = signToken.getJWTClaimsSet().getSubject();
        Date expiryTime = signToken.getJWTClaimsSet().getExpirationTime();

        RefreshToken refreshToken1 = RefreshToken.builder()
                .user(userRepository.findByUserName(userName).get())
                .token(refreshToken.getToken())
                .expiryTime(expiryTime)
                .build();

        refreshTokenRepository.save(refreshToken1);

        var user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new AppException(ErrorException.UNAUTHORIZED));

        // tạo token mới lấy từ username của token cũ
        var token = createToken(user);

        return AuthenticationResponse.builder()
                .token(token)
                .check(true)
                .build();
    }

    private String createToken(User user) {
        // thuật toán mã hóa
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        // build payload cần Claim
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUserName())    // claim đại diện cho username
                .issuer("quandeptrai.com")      // token đuược issuer từ ai (thường là domain)
                .issueTime(new Date())
                .expirationTime(new Date(       // thời hạn token tồn tại
                        Instant.now().plus(VALID_DURATION, ChronoUnit.SECONDS).toEpochMilli()
                ))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope", buildScope(user))
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);

        // ký token
        try {
            jwsObject.sign(new MACSigner(KEY_SINGER.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Can't create token", e);
            throw new RuntimeException();
        }
    }


    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
        var token = request.getToken();
        boolean isValid = true;
        try {
            verifyToken(token, false);
        } catch (AppException e) {
            isValid = false;
        }

        return IntrospectResponse.builder()
                .valid(isValid)
                .build();
    }

    private SignedJWT verifyToken(String token, boolean isRefresh) throws ParseException, JOSEException {
        JWSVerifier verifier = new MACVerifier(KEY_SINGER.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expiryTime = (isRefresh)
                ? new Date(signedJWT.getJWTClaimsSet().getIssueTime()   // nếu đúng để refresh token
                .toInstant().plus(REFRESHABLE_DURATION, ChronoUnit.SECONDS).toEpochMilli())     // nếu sai để logout
                : signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified = signedJWT.verify(verifier);

        if (!verified && expiryTime.after(new Date())) {
            throw new AppException(ErrorException.UNAUTHORIZED);
        }

        if (refreshTokenRepository.existsByToken(token)) {
            throw new AppException(ErrorException.UNAUTHORIZED);
        }
        ;

        return signedJWT;
    }

    private String buildScope(User user) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        if (!CollectionUtils.isEmpty(user.getRoles()))
            user.getRoles().forEach(role -> {
                stringJoiner.add("ROLE_" + role.getRoleName());
            });

        return stringJoiner.toString();
    }
}

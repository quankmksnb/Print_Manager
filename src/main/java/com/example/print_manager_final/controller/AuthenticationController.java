package com.example.print_manager_final.controller;

import com.example.print_manager_final.dto.request.AuthenticationRequest;
import com.example.print_manager_final.dto.request.IntrospectRequest;
import com.example.print_manager_final.dto.request.LogoutRequest;
import com.example.print_manager_final.dto.request.RefreshTokenRequest;
import com.example.print_manager_final.dto.response.ApiResponse;
import com.example.print_manager_final.dto.response.AuthenticationResponse;
import com.example.print_manager_final.dto.response.IntrospectResponse;
import com.example.print_manager_final.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ApiResponse<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {
        return ApiResponse.<AuthenticationResponse>builder()
                .result(authenticationService.authenticate(authenticationRequest))
                .code(200)
                .build();
    }

    @RequestMapping(value = "introspect", method = RequestMethod.POST)
    public ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest introspectRequest) throws ParseException, JOSEException {
        return ApiResponse.<IntrospectResponse>builder()
                .result(authenticationService.introspect(introspectRequest))
                .code(200)
                .build();
    }


    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public ApiResponse<Void> logout(@RequestBody LogoutRequest request) throws ParseException, JOSEException {
        authenticationService.logout(request);
        return ApiResponse.<Void>builder()
                .code(200)
                .build();
    }

    @RequestMapping(value = "refresh", method = RequestMethod.POST)
    public ApiResponse<AuthenticationResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) throws ParseException, JOSEException {
        return ApiResponse.<AuthenticationResponse>builder()
                .result(authenticationService.refreshToken(refreshTokenRequest))
                .code(200)
                .build();
    }
}

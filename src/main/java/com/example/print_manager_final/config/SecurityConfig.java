package com.example.print_manager_final.config;

import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableWebSecurity  // phần quyền với enpoint
@EnableMethodSecurity   // phân quyền trên method
public class SecurityConfig {
    private final String[] PUBLIC_POST_ENDPOINT = {"/user/signup_user", "/auth/login", "/auth/introspect", "/auth/logout", "/auth/refresh"};
    private final String[] PUBLIC_GET_ENDPOINT = {"/user/myInfo"};

    @Autowired
    private CustomJwtDecoder customJwtDecoder;

//    @NonFinal
//    @Value("${jwt.keySigner}")      // đọc biến từ file properties
//    protected String KEY_SINGER;

    // trình mã hóa bằng BCrypt
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(request ->
                request.requestMatchers(HttpMethod.POST, PUBLIC_POST_ENDPOINT).permitAll()
                        .requestMatchers(HttpMethod.GET, PUBLIC_GET_ENDPOINT).permitAll()
                        .anyRequest().authenticated()
        );
        // cấu hình nếu token hợp lẹ thì được truy cập
        httpSecurity.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwtConfigurer ->
                jwtConfigurer.decoder(customJwtDecoder)
                        .jwtAuthenticationConverter(jwtAuthenticationConverter())
        ));        // giúp decode token truyền vào

        // spring security mặc định bật cấu hình của csrf nên phải tắt
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        return httpSecurity.build();
    }

//    @Bean
//    JwtDecoder jwtDecoder() {
//        SecretKeySpec secretKeySpec = new SecretKeySpec(KEY_SINGER.getBytes(), "HS512");
//        return NimbusJwtDecoder
//                .withSecretKey(secretKeySpec)
//                .macAlgorithm(MacAlgorithm.HS512)
//                .build();
//    }

    // design prefix scope = role
    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter converter = new JwtGrantedAuthoritiesConverter();
        converter.setAuthorityPrefix("");
        JwtAuthenticationConverter converter1 = new JwtAuthenticationConverter();
        converter1.setJwtGrantedAuthoritiesConverter(converter);
        return converter1;
    }
}

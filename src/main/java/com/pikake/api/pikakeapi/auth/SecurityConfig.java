package com.pikake.api.pikakeapi.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http
//                .authorizeHttpRequests(auth -> {
////                    auth.requestMatchers("/api/v1/users").permitAll();
//                    auth.requestMatchers("/api/v1/csrf").permitAll();
//                    auth.anyRequest().authenticated();
//                })
////                .csrf(csrf -> {
////                    csrf.ignoringRequestMatchers("/api/v1/users/{id}");
////                })
////                .csrf((csrf) -> csrf
////                        .csrfTokenRepository(new HttpSessionCsrfTokenRepository())
////                )
//                .build();
//    }

}
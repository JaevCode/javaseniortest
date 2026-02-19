package com.jaevcode.invex.oauth2server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class LoginSecurityConfig {
    @Bean
    SecurityFilterChain appSecurity(HttpSecurity http, RemoteUserAuthProvider provider) throws Exception {
        http
                .securityMatcher("/login", "/assets/**", "/error")
                .authorizeHttpRequests(a -> a.anyRequest().permitAll())
                .authenticationProvider(provider)
                .formLogin(Customizer.withDefaults());

        return http.build();
    }
}

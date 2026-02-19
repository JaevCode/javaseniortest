package com.jaevcode.invex.oauth2server.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.ErrorResponse;
import org.springframework.web.client.RestClient;

import java.util.Arrays;

@Component
public class RemoteUserAuthProvider implements AuthenticationProvider {
    private final RestClient restClient;

    public RemoteUserAuthProvider(
            @Qualifier("restClientVerifyUser") RestClient restClient
    ) {
        this.restClient = restClient;
    }

    record VerifyRequest(String username, String password) {
    }

    record VerifyResponse(Boolean isValid, String rolesCsv) {
    }

    public record BaseResponse<T>(
            Integer status,
            String statusMessage,
            T payload,
            ErrorResponse error
    ) {
    }

    @Override
    public Authentication authenticate(Authentication authentication) {
        String username = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());

        BaseResponse<VerifyResponse> response = restClient.post()
                .body(new VerifyRequest(username, password))
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });

        VerifyResponse resp = response.payload;

        if (resp == null || !resp.isValid()) {
            throw new BadCredentialsException("Invalid credentials");
        }

        var authorities = Arrays.stream(resp.rolesCsv.split(","))
                .map(SimpleGrantedAuthority::new)
                .toList();

        return new UsernamePasswordAuthenticationToken(username, null, authorities);
    }

    @Override
    public boolean supports(Class<?> authType) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authType);
    }
}

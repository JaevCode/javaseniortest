package com.jaevcode.invex.employees.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class ResourceServerSecurity {
    @Bean
    SecurityFilterChain security(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(a -> a
                        .requestMatchers("/actuator/health", "/actuator/info").permitAll()
                        // lectura: cliente con scope o usuario con rol
                        .requestMatchers(HttpMethod.GET, "/employees/**")
                        .hasAnyAuthority("SCOPE_employees.read", "EMPLOYEE", "ADMIN")
                        // escritura: cliente con scope o usuario admin
                        .requestMatchers(HttpMethod.POST, "/employees/**")
                        .hasAnyAuthority("SCOPE_employees.write", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/employees/**")
                        .hasAnyAuthority("SCOPE_employees.write", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/employees/**")
                        .hasAnyAuthority("SCOPE_employees.write", "ADMIN")
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(o -> o
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
                );

        return http.build();
    }

    @Bean
    Converter<Jwt, ? extends AbstractAuthenticationToken> jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter scopes = new JwtGrantedAuthoritiesConverter();
        // default: scope/scp -> SCOPE_xxx

        Converter<Jwt, Collection<GrantedAuthority>> roles = jwt -> {
            List<String> claims = jwt.getClaimAsStringList("roles");
            if (claims == null) return List.of();
            return claims.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toCollection(ArrayList::new));
        };

        return jwt -> {
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            authorities.addAll(scopes.convert(jwt));
            authorities.addAll(roles.convert(jwt));
            return new JwtAuthenticationToken(jwt, authorities);
        };
    }
}

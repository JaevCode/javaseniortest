package com.jaevcode.invex.oauth2server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfiguration {
    @Bean
    public RestClient restClientVerifyUser(RestClient.Builder builder, @Value("${app.client.verifyUser.url}") String verifyUserUrl) {
        return builder
                .baseUrl(verifyUserUrl)
                .build();
    }
}

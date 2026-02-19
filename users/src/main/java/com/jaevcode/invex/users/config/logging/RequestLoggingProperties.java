package com.jaevcode.invex.users.config.logging;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "logging.requests")
public class RequestLoggingProperties {
    private boolean enabled = true;
    private String filePath;
    private String maskedFieldsConfig;
}

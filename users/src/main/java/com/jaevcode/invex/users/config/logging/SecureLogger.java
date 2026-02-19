package com.jaevcode.invex.users.config.logging;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SecureLogger {

    private static final Logger log = LoggerFactory.getLogger(SecureLogger.class);
    private final MaskedFieldService maskedFieldService;
    private final ObjectMapper objectMapper;

    public SecureLogger(MaskedFieldService maskedFieldService) {
        this.maskedFieldService = maskedFieldService;
        this.objectMapper = new ObjectMapper();
    }

    public void log(String message, Object data) {
        try {
            String json = objectMapper.writeValueAsString(data);
            String masked = maskedFieldService.maskJsonFields(json);
            log.info("{}: {}", message, masked);
        } catch (Exception e) {
            log.warn("Error logging masked object: {}", e.getMessage());
        }
    }
}

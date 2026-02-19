package com.jaevcode.invex.employees.config.logging;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;

@Component
@Order(1)
public class LoggingFilter extends OncePerRequestFilter {

    private final RequestLoggingProperties properties;
    private final MaskedFieldService maskedFieldService;
    private static final Logger log = LoggerFactory.getLogger(LoggingFilter.class);

    public LoggingFilter(RequestLoggingProperties properties, MaskedFieldService maskedFieldService) {
        this.properties = properties;
        this.maskedFieldService = maskedFieldService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        if (!properties.isEnabled()) {
            filterChain.doFilter(request, response);
            return;
        }

        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request, 0);
        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);

        Instant start = Instant.now();
        filterChain.doFilter(wrappedRequest, wrappedResponse);
        Instant end = Instant.now();

        long duration = Duration.between(start, end).toMillis();

        String requestBody = new String(wrappedRequest.getContentAsByteArray(), StandardCharsets.UTF_8);
        String responseBody = new String(wrappedResponse.getContentAsByteArray(), StandardCharsets.UTF_8);

        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null) ip = request.getRemoteAddr();

        String logEntry = String.format("""
            REQUEST %s %s (%dms)
            IP: %s
            Body: %s

            RESPONSE %d
            Body: %s
            """,
            request.getMethod(),
            request.getRequestURI(),
            duration,
            ip,
            maskedFieldService.maskJsonFields(requestBody),
            response.getStatus(),
            maskedFieldService.maskJsonFields(responseBody)
        );

        log.info(logEntry);

        if (properties.getFilePath() != null && !properties.getFilePath().isBlank()) {
            try (FileWriter fw = new FileWriter(properties.getFilePath(), true)) {
                fw.write(logEntry + System.lineSeparator());
            } catch (IOException e) {
                log.warn("No se pudo escribir el log en archivo: {}", e.getMessage());
            }
        }

        wrappedResponse.copyBodyToResponse();
    }
}

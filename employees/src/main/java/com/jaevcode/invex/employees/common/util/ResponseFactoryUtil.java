package com.jaevcode.invex.employees.common.util;

import com.jaevcode.invex.employees.adapter.in.rest.dto.common.BaseResponse;
import com.jaevcode.invex.employees.adapter.in.rest.dto.common.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ResponseFactoryUtil {

    public static <T> ResponseEntity<BaseResponse<T>> createResponse(HttpStatus status, T payload, String statusMessage) {
        return ResponseEntity.status(status).body(BaseResponse.<T>builder()
                .status(status.value())
                .statusMessage(statusMessage)
                .payload(payload)
                .build());
    }

    public static ResponseEntity<BaseResponse<Void>> createErrorResponse(HttpStatus status, String statusMessage, String... errorMessages) {
        return ResponseEntity.status(status).body(BaseResponse.<Void>builder()
                .status(status.value())
                .statusMessage(statusMessage)
                .error(ErrorResponse.builder()
                        .messages(List.of(errorMessages))
                        .build())
                .build());
    }

}

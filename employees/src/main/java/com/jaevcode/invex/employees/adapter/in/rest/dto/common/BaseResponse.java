package com.jaevcode.invex.employees.adapter.in.rest.dto.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record BaseResponse<T>(
        Integer status,
        String statusMessage,
        T payload,
        ErrorResponse error
) {
}

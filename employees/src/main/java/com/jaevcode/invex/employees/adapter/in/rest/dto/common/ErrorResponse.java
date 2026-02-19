package com.jaevcode.invex.employees.adapter.in.rest.dto.common;

import lombok.Builder;

import java.util.List;

@Builder
public record ErrorResponse(
        List<String> messages
) {
}

package com.jaevcode.invex.users.adapter.in.rest.dto.verifyuser;

import io.swagger.v3.oas.annotations.media.Schema;

public record VerifyUserResponseDto(@Schema(description = "Roles", example = "ADMIN,EMPLOYEE")
                                    String rolesCsv,
                                    @Schema(description = "Is valid?", example = "true")
                                    Boolean isValid) {
}

package com.jaevcode.invex.users.adapter.in.rest.dto.common;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UserDto(@Schema(description = "Id", example = "1")
                      @NotNull @NotEmpty String id,
                      @Schema(description = "Username", example = "jaevcode")
                      @NotNull @NotEmpty String username,
                      @Schema(description = "Roles", example = "ADMIN,EMPLOYEE")
                      @NotNull @NotEmpty String rolesCsv) {
}

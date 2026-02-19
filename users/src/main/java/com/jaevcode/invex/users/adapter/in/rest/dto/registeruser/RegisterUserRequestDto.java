package com.jaevcode.invex.users.adapter.in.rest.dto.registeruser;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RegisterUserRequestDto(@Schema(description = "Username", example = "jaevcode")
                                  @NotNull @NotEmpty String username,
                                     @Schema(description = "Password", example = "123")
                                  @NotNull @NotEmpty String password,
                                     @Schema(description = "Roles", example = "ADMIN,EMPLOYEE")
                                  @NotNull @NotEmpty String rolesCsv) {
}

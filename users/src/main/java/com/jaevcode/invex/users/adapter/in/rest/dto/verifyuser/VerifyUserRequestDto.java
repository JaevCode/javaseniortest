package com.jaevcode.invex.users.adapter.in.rest.dto.verifyuser;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record VerifyUserRequestDto(@Schema(description = "Username", example = "jaevcode")
                                @NotNull @NotEmpty String username,
                                   @Schema(description = "Password", example = "123")
                                @NotNull @NotEmpty String password) {
}

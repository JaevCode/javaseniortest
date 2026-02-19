package com.jaevcode.invex.users.adapter.in.rest.controller;

import com.jaevcode.invex.users.adapter.in.rest.dto.common.BaseResponse;
import com.jaevcode.invex.users.adapter.in.rest.dto.verifyuser.VerifyUserRequestDto;
import com.jaevcode.invex.users.adapter.in.rest.dto.verifyuser.VerifyUserResponseDto;
import com.jaevcode.invex.users.adapter.in.rest.mapper.VerifyUserMapper;
import com.jaevcode.invex.users.application.port.in.VerifyUserUseCase;
import com.jaevcode.invex.users.common.util.ResponseFactoryUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Tag(name = "Verify User", description = "Verify user service")
public class VerifyUserController {

    private final VerifyUserUseCase verifyUserUseCase;

    private final VerifyUserMapper verifyUserMapper;

    @PostMapping("/auth/verify")
    @Operation(summary = "Verify a user", description = "Verify user")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User verified.",
                    content = @Content(schema = @Schema(implementation = VerifyUserResponseDto.class)))
    })
    public ResponseEntity<BaseResponse<VerifyUserResponseDto>> registerUser(@RequestBody @Valid VerifyUserRequestDto request) {
        VerifyUserResponseDto verifyUserResponseDto = verifyUserMapper.modelResultToDtoResponse(verifyUserUseCase.verifyUser(verifyUserMapper.modelToDto(request)));
        return ResponseFactoryUtil.createResponse(HttpStatus.OK, verifyUserResponseDto, "Ok");
    }
}

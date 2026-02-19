package com.jaevcode.invex.users.adapter.in.rest.controller;

import com.jaevcode.invex.users.adapter.in.rest.dto.common.BaseResponse;
import com.jaevcode.invex.users.adapter.in.rest.dto.common.UserDto;
import com.jaevcode.invex.users.adapter.in.rest.dto.registeruser.RegisterUserRequestDto;
import com.jaevcode.invex.users.adapter.in.rest.mapper.UserDtoMapper;
import com.jaevcode.invex.users.application.port.in.RegisterUsernameUseCase;
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
@Tag(name = "Register User", description = "Register user service")
public class RegisterUserController {

    private final RegisterUsernameUseCase registerUsernameUseCase;
    private final UserDtoMapper userDtoMapper;

    @PostMapping("/users")
    @Operation(summary = "Register a user", description = "Register user")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User created.",
                    content = @Content(schema = @Schema(implementation = UserDto.class)))
    })
    public ResponseEntity<BaseResponse<UserDto>> registerUser(@RequestBody @Valid RegisterUserRequestDto request) {
        UserDto userDto = userDtoMapper.modelToDto(registerUsernameUseCase.registerUser(userDtoMapper.registerUserRequestDtoToUser(request)));
        return ResponseFactoryUtil.createResponse(HttpStatus.CREATED, userDto, "Created");
    }
}

package com.jaevcode.invex.users.adapter.in.rest.mapper;

import com.jaevcode.invex.users.adapter.in.rest.dto.common.UserDto;
import com.jaevcode.invex.users.adapter.in.rest.dto.registeruser.RegisterUserRequestDto;
import com.jaevcode.invex.users.domain.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {

    UserDto modelToDto(User user);

    User dtoToModel(UserDto dto);

    RegisterUserRequestDto modelToRegisterUserRequestDto(User user);

    User registerUserRequestDtoToUser(RegisterUserRequestDto registerUserRequestDto);
}

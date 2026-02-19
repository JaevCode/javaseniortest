package com.jaevcode.invex.users.adapter.in.rest.mapper;

import com.jaevcode.invex.users.adapter.in.rest.dto.verifyuser.VerifyUserRequestDto;
import com.jaevcode.invex.users.adapter.in.rest.dto.verifyuser.VerifyUserResponseDto;
import com.jaevcode.invex.users.domain.model.VerifyUser;
import com.jaevcode.invex.users.domain.model.VerifyUserResult;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VerifyUserMapper {
    VerifyUser modelToDto(VerifyUserRequestDto verifyUserRequestDto);

    VerifyUserRequestDto dtoToModel(VerifyUser verifyUser);

    VerifyUserResponseDto modelResultToDtoResponse(VerifyUserResult verifyUserResponseDto);

    VerifyUserResult dtoResponseToModelResult(VerifyUserResponseDto verifyUserResult);
}

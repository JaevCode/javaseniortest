package com.jaevcode.invex.users.adapter.out.persistence.mapper;

import com.jaevcode.invex.users.adapter.out.persistence.entity.UserJpaEntity;
import com.jaevcode.invex.users.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserJpaMapper {

    @Mapping(target = "passwordHash", source = "password")
    UserJpaEntity modelToJpaEntity(User user);

    @Mapping(target = "password", source = "passwordHash")
    User jpaEntityToModel(UserJpaEntity userJpaEntity);
}

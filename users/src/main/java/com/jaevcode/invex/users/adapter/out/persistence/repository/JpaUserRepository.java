package com.jaevcode.invex.users.adapter.out.persistence.repository;

import com.jaevcode.invex.users.adapter.out.persistence.entity.UserJpaEntity;
import com.jaevcode.invex.users.adapter.out.persistence.mapper.UserJpaMapper;
import com.jaevcode.invex.users.application.port.out.UserRepository;
import com.jaevcode.invex.users.domain.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class JpaUserRepository implements UserRepository {

    private JpaUserSpringDataRepository jpaUserSpringDataRepository;
    private UserJpaMapper userJpaMapper;

    @Override
    public User save(User user) {
        UserJpaEntity userJpaEntity = jpaUserSpringDataRepository.save(userJpaMapper.modelToJpaEntity(user));
        return userJpaMapper.jpaEntityToModel(userJpaEntity);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return jpaUserSpringDataRepository.findByUsername(username).map(userJpaMapper::jpaEntityToModel);
    }
}

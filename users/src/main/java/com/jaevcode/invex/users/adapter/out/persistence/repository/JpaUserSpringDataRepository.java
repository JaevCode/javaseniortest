package com.jaevcode.invex.users.adapter.out.persistence.repository;

import com.jaevcode.invex.users.adapter.out.persistence.entity.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaUserSpringDataRepository extends JpaRepository<UserJpaEntity, Long> {
    public Optional<UserJpaEntity> findByUsername(String username);

}

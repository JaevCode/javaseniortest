package com.jaevcode.invex.users.application.port.out;

import com.jaevcode.invex.users.domain.model.User;

import java.util.Optional;

public interface UserRepository {
    public User save(User user);
    public Optional<User> findByUsername(String username);
}

package com.jaevcode.invex.users.application.service;

import com.jaevcode.invex.users.application.port.in.RegisterUsernameUseCase;
import com.jaevcode.invex.users.application.port.out.EncryptRepository;
import com.jaevcode.invex.users.application.port.out.UserRepository;
import com.jaevcode.invex.users.domain.model.User;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegisterUsernameService implements RegisterUsernameUseCase {

    private UserRepository userRepository;
    private EncryptRepository encodeRepository;

    @Override
    public User registerUser(User user) {
        user.setPassword(encodeRepository.encode(user.getPassword()));
        return userRepository.save(user);
    }
}

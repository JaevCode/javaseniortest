package com.jaevcode.invex.users.application.port.in;

import com.jaevcode.invex.users.domain.model.User;

public interface RegisterUsernameUseCase {
    public User registerUser(User user);
}

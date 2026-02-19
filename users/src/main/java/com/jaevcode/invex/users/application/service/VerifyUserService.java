package com.jaevcode.invex.users.application.service;

import com.jaevcode.invex.users.application.port.in.VerifyUserUseCase;
import com.jaevcode.invex.users.application.port.out.EncryptRepository;
import com.jaevcode.invex.users.application.port.out.UserRepository;
import com.jaevcode.invex.users.common.exception.BusinessValidationException;
import com.jaevcode.invex.users.domain.model.User;
import com.jaevcode.invex.users.domain.model.VerifyUser;
import com.jaevcode.invex.users.domain.model.VerifyUserResult;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class VerifyUserService implements VerifyUserUseCase {

    private UserRepository userRepository;
    private EncryptRepository encodeRepository;

    @Override
    public VerifyUserResult verifyUser(VerifyUser user) {
        Optional<User> optionalSearchedUser = userRepository.findByUsername(user.getUsername());
        if (optionalSearchedUser.isEmpty()) {
            throw new BusinessValidationException("Username not found");
        }
        User searchedUser = optionalSearchedUser.get();
        boolean isPasswordValid = encodeRepository.matches(user.getPassword(), searchedUser.getPassword());
        if (!isPasswordValid) {
            throw new BusinessValidationException("Username not found");
        }

        return VerifyUserResult.builder()
                .isValid(true)
                .rolesCsv(searchedUser.getRolesCsv())
                .build();
    }
}

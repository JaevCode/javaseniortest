package com.jaevcode.invex.users.application.port.in;

import com.jaevcode.invex.users.domain.model.VerifyUser;
import com.jaevcode.invex.users.domain.model.VerifyUserResult;

public interface VerifyUserUseCase {
    public VerifyUserResult verifyUser(VerifyUser user);
}

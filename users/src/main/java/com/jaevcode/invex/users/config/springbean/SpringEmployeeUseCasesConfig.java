package com.jaevcode.invex.users.config.springbean;

import com.jaevcode.invex.users.application.port.in.RegisterUsernameUseCase;
import com.jaevcode.invex.users.application.port.in.VerifyUserUseCase;
import com.jaevcode.invex.users.application.port.out.EncryptRepository;
import com.jaevcode.invex.users.application.port.out.UserRepository;
import com.jaevcode.invex.users.application.service.RegisterUsernameService;
import com.jaevcode.invex.users.application.service.VerifyUserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class SpringEmployeeUseCasesConfig {

    private UserRepository userRepository;
    private EncryptRepository encryptRepository;

    @Bean
    RegisterUsernameUseCase registerUsernameService() {
        return new RegisterUsernameService(userRepository, encryptRepository);
    }

    @Bean
    VerifyUserUseCase verifyUserService() {
        return new VerifyUserService(userRepository, encryptRepository);
    }

}

package com.jaevcode.invex.users.application.service;

import com.jaevcode.invex.users.application.port.out.EncryptRepository;
import com.jaevcode.invex.users.application.port.out.UserRepository;
import com.jaevcode.invex.users.common.exception.BusinessValidationException;
import com.jaevcode.invex.users.domain.model.User;
import com.jaevcode.invex.users.domain.model.VerifyUser;
import com.jaevcode.invex.users.domain.model.VerifyUserResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VerifyUserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private EncryptRepository encodeRepository;

    @InjectMocks
    private VerifyUserService service;

    @Test
    void verifyUser_throwsWhenUsernameDoesNotExist() {
        VerifyUser request = VerifyUser.builder()
                .username("jose")
                .password("pass")
                .build();
        when(userRepository.findByUsername("jose")).thenReturn(Optional.empty());

        BusinessValidationException ex = assertThrows(
                BusinessValidationException.class,
                () -> service.verifyUser(request)
        );

        assertEquals("Username not found", ex.getMessage());
        verify(encodeRepository, never()).matches(request.getPassword(), "any");
    }

    @Test
    void verifyUser_throwsWhenPasswordDoesNotMatch() {
        VerifyUser request = VerifyUser.builder()
                .username("jose")
                .password("wrong-pass")
                .build();
        User foundUser = User.builder()
                .username("jose")
                .password("encoded-pass")
                .rolesCsv("ROLE_USER")
                .build();

        when(userRepository.findByUsername("jose")).thenReturn(Optional.of(foundUser));
        when(encodeRepository.matches("wrong-pass", "encoded-pass")).thenReturn(false);

        BusinessValidationException ex = assertThrows(
                BusinessValidationException.class,
                () -> service.verifyUser(request)
        );

        assertEquals("Username not found", ex.getMessage());
    }

    @Test
    void verifyUser_returnsValidResultWhenCredentialsAreCorrect() {
        VerifyUser request = VerifyUser.builder()
                .username("jose")
                .password("correct-pass")
                .build();
        User foundUser = User.builder()
                .username("jose")
                .password("encoded-pass")
                .rolesCsv("ROLE_USER,ROLE_ADMIN")
                .build();

        when(userRepository.findByUsername("jose")).thenReturn(Optional.of(foundUser));
        when(encodeRepository.matches("correct-pass", "encoded-pass")).thenReturn(true);

        VerifyUserResult result = service.verifyUser(request);

        assertTrue(result.getIsValid());
        assertEquals("ROLE_USER,ROLE_ADMIN", result.getRolesCsv());
    }
}

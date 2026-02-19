package com.jaevcode.invex.users.application.service;

import com.jaevcode.invex.users.application.port.out.EncryptRepository;
import com.jaevcode.invex.users.application.port.out.UserRepository;
import com.jaevcode.invex.users.domain.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegisterUsernameServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private EncryptRepository encodeRepository;

    @InjectMocks
    private RegisterUsernameService service;

    @Test
    void registerUser_encodesPasswordAndSavesUser() {
        User inputUser = User.builder()
                .username("jose")
                .password("plain-password")
                .rolesCsv("ROLE_USER")
                .build();
        User savedUser = User.builder()
                .id(10L)
                .username("jose")
                .password("encoded-password")
                .rolesCsv("ROLE_USER")
                .build();

        when(encodeRepository.encode("plain-password")).thenReturn("encoded-password");
        when(userRepository.save(inputUser)).thenReturn(savedUser);

        User result = service.registerUser(inputUser);

        assertSame(savedUser, result);
        assertEquals("encoded-password", inputUser.getPassword());

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userCaptor.capture());
        assertEquals("encoded-password", userCaptor.getValue().getPassword());
    }
}

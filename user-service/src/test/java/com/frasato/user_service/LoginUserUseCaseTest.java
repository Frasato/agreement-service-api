package com.frasato.user_service;

import com.frasato.user_service.application.usecases.LoginUserUseCase;
import com.frasato.user_service.domain.model.User;
import com.frasato.user_service.domain.repository.UserRepository;
import com.frasato.user_service.domain.service.TokenService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LoginUserUseCaseTest {

    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private UserRepository userRepository;
    @Mock
    private TokenService tokenService;
    @InjectMocks
    private LoginUserUseCase loginUserUseCase;

    @Test
    void shouldLoginUserSuccess(){
        User user = createUser();

        when(userRepository.findUserByDocument("96586565326")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("123", user.getPassword())).thenReturn(true);
        when(tokenService.generateToken(user)).thenReturn("generated_token_with_token_service");

        var result = loginUserUseCase.login("96586565326", "123");

        assertEquals("generated_token_with_token_service", result.token());
        assertEquals("1", result.id());
    }

    @Test
    void shouldThrowExceptionWhenPasswordWrong(){
        User user = createUser();

        when(userRepository.findUserByDocument("96586565326")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("123", user.getPassword())).thenReturn(false);

        assertThrows(RuntimeException.class, ()->{
            loginUserUseCase.login("96586565326", "123");
        });

        verify(tokenService, never()).generateToken(any());
    }

    @Test
    void shouldThrowExceptionAndStopFlowWhenUserNotFound(){
        when(userRepository.findUserByDocument("96586565326")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, ()->{
            loginUserUseCase.login("96586565326", "123");
        });

        verify(passwordEncoder, never()).matches(any(), any());
        verify(tokenService, never()).generateToken(any());
    }

    @Test
    void shouldGenerateTokenWhenSuccessLogin(){
        User user = createUser();

        when(userRepository.findUserByDocument("96586565326")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("123", user.getPassword())).thenReturn(true);
        when(tokenService.generateToken(user)).thenReturn("generated_token_with_token_service");

        loginUserUseCase.login("96586565326", "123");

        verify(tokenService, times(1)).generateToken(any());
    }

    private User createUser(){
        return new User(
                "1",
                "Gabriel Silva dos Santos",
                "17991568457",
                "96586565326",
                "Rua Aurora Forte Neves",
                "123_encoded",
                "ROLE_USER"
        );
    }
}
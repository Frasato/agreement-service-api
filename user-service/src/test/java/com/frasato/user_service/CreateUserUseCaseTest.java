package com.frasato.user_service;

import com.frasato.user_service.application.usecases.RegisterUserUseCase;
import com.frasato.user_service.domain.model.User;
import com.frasato.user_service.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateUserUseCaseTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private RegisterUserUseCase registerUserUseCase;

    @Test
    void shouldCreateUserSuccessfully(){
        User user = new User(
                "1",
                "Gabriel Silva dos Santos",
                "17991568457",
                "96586565326",
                "Rua Aurora Forte Neves",
                "123",
                "ROLE_USER"
        );

        when(userRepository.saveUser(any(User.class))).thenReturn(user);
        when(passwordEncoder.encode(any(CharSequence.class))).thenReturn("123-encoded");

        User result = registerUserUseCase.register(user);
        assertEquals(user.getName(), result.getName());
    }

    @Test
    void shouldThrowExceptionWhenDocumentIsEmpty(){
        User user = new User(
            "1",
            "Gabriel Silva dos Santos",
            "17991568457",
            "",
            "Rua Aurora Forte Neves",
            "123",
            "ROLE_USER"
        );

        assertThrows(RuntimeException.class, () ->{
            registerUserUseCase.register(user);
        });

        verify(userRepository, never()).saveUser(any());
    }

    @Test
    void shouldThrowExceptionWhenNameIsEmpty(){
        User user = new User(
                "1",
                "",
                "17991568457",
                "96586565326",
                "Rua Aurora Forte Neves",
                "123",
                "ROLE_USER"
        );

        assertThrows(RuntimeException.class, () ->{
            registerUserUseCase.register(user);
        });

        verify(userRepository, never()).saveUser(any());
    }

    @Test
    void shouldNotCreateUserWithWrongRole(){
        User user = new User(
                "1",
                "Gabriel Silva dos Santos",
                "17991568457",
                "96586565326",
                "Rua Aurora Forte Neves",
                "123",
                "ROLE_MANAGER"
        );

        assertThrows(RuntimeException.class, () ->{
            registerUserUseCase.register(user);
        });

        verify(userRepository, never()).saveUser(any());
    }

    @Test
    void shouldSaveUserWithEncryptedPassword(){
        User user = new User(
                "1",
                "Gabriel Silva dos Santos",
                "17991568457",
                "96586565326",
                "Rua Aurora Forte Neves",
                "123",
                "ROLE_USER"
        );

        when(passwordEncoder.encode("123")).thenReturn("123-encoded");
        when(userRepository.saveUser(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        User result = registerUserUseCase.register(user);

        verify(passwordEncoder, times(1)).encode("123");
        assertEquals("123-encoded", result.getPassword());
    }

    @Test
    void shouldNotCreateUserWhenDocumentAlreadyExists(){
        User user = new User(
                "1",
                "Gabriel Silva dos Santos",
                "17991568457",
                "96586565326",
                "Rua Aurora Forte Neves",
                "123",
                "ROLE_USER"
        );

        when(userRepository.findUserByDocument(user.getDocument()))
                .thenReturn(Optional.of(user));

        assertThrows(RuntimeException.class, () -> {
            registerUserUseCase.register(user);
        });

        verify(userRepository, never()).saveUser(any());
    }
}
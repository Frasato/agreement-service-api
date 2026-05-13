package com.frasato.user_service;

import com.frasato.user_service.application.usecases.AddFavoriteConsortiumUseCase;
import com.frasato.user_service.domain.model.User;
import com.frasato.user_service.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AddConsortiumOnUserUseCaseTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private AddFavoriteConsortiumUseCase favoriteConsortiumUseCase;

    @Test
    void shouldAddConsortiumInUser(){
        User user = createUser();

        when(userRepository.findById("1")).thenReturn(user);
        when(userRepository.saveConsortiumOnUser(user)).thenReturn(user);

        User result = favoriteConsortiumUseCase.add("1", "consortium_id_2");
        verify(userRepository, times(1)).saveConsortiumOnUser(user);
        assertEquals(3, result.getConsortiumIds().toArray().length);
    }

    @Test
    void shouldThrowExceptionWhenParamIsMissing(){
        User user = createUser();

        assertThrows(RuntimeException.class, () -> {
            favoriteConsortiumUseCase.add("1", "");
        });
        verify(userRepository, never()).saveConsortiumOnUser(user);
    }

    @Test
    void shouldThrowExceptionWhenConsortiumIdAlreadyExist(){
        User user = createUser();

        when(userRepository.findById("1")).thenReturn(user);
        assertThrows(RuntimeException.class, () -> {
            favoriteConsortiumUseCase.add("1", "consortium_id_1");
        });
        verify(userRepository, never()).saveConsortiumOnUser(user);
    }

    private User createUser(){
        return new User(
                "1",
                "Gabriel Silva dos Santos",
                "17991568457",
                "96586565326",
                "Rua Aurora Forte Neves",
                "123_encoded",
                "ROLE_USER",
                new ArrayList<>(List.of("consortium_id_0", "consortium_id_1"))
        );
    }
}
package com.frasato.user_service;

import com.frasato.user_service.application.dto.LoginResult;
import com.frasato.user_service.application.usecases.LoginUserUseCase;
import com.frasato.user_service.application.usecases.RegisterUserUseCase;
import com.frasato.user_service.domain.model.User;
import com.frasato.user_service.infra.web.controller.UserController;
import com.frasato.user_service.infra.web.dto.UserLoginRequestDto;
import com.frasato.user_service.infra.web.dto.UserLoginResponseDto;
import com.frasato.user_service.infra.web.dto.UserRegisterRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    UserController userController;
    @Mock
    RegisterUserUseCase registerUserUseCase;
    @Mock
    LoginUserUseCase loginUserUseCase;

    @Test
    void shouldRegisterNewUser(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        User user = createUser();

        when(registerUserUseCase.register(any(User.class))).thenReturn(user);

        UserRegisterRequestDto requestDto = new UserRegisterRequestDto(
                user.getName(),
                user.getPhone(),
                user.getDocument(),
                user.getAddress(),
                user.getPassword()
        );

        ResponseEntity<String> responseEntity = userController.register(requestDto);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        verify(registerUserUseCase, times(1)).register(any(User.class));
    }

    @Test
    void shouldLoginUser(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        LoginResult loginResult = new LoginResult("123", "1");
        when(loginUserUseCase.login(any(), any())).thenReturn(loginResult);

        UserLoginRequestDto loginRequestDto = new UserLoginRequestDto("55544466600", "123");

        ResponseEntity<UserLoginResponseDto> responseEntity = userController.login(loginRequestDto);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("123",responseEntity.getBody().token());
        verify(loginUserUseCase, times(1)).login(any(), any());
    }

    private User createUser(){
        return new User(
                null,
                "Gabriel Silva dos Santos",
                "17991568457",
                "96586565326",
                "Rua Aurora Forte Neves",
                "123_encoded",
                "ROLE_USER"
        );
    }
}

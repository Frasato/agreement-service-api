package com.frasato.user_service.infra.web.controller;

import com.frasato.user_service.application.usecases.LoginUserUseCase;
import com.frasato.user_service.application.usecases.RegisterUserUseCase;
import com.frasato.user_service.domain.model.User;
import com.frasato.user_service.infra.web.dto.UserLoginRequestDto;
import com.frasato.user_service.infra.web.dto.UserLoginResponseDto;
import com.frasato.user_service.infra.web.dto.UserRegisterRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final RegisterUserUseCase registerUserUseCase;
    private final LoginUserUseCase loginUserUseCase;

    public UserController(RegisterUserUseCase registerUserUseCase, LoginUserUseCase loginUserUseCase){
        this.registerUserUseCase = registerUserUseCase;
        this.loginUserUseCase = loginUserUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegisterRequestDto requestDto){
        User userDomain = new User(
                null,
                requestDto.name(),
                requestDto.phone(),
                requestDto.document(),
                requestDto.address(),
                requestDto.password()
        );

        registerUserUseCase.register(userDomain);
        return ResponseEntity.status(HttpStatus.CREATED).body("User register success!");
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> login(@RequestBody UserLoginRequestDto loginRequestDto){
        User user = loginUserUseCase.login(loginRequestDto.document(), loginRequestDto.password());
        return ResponseEntity.ok().body(new UserLoginResponseDto(user.getId()));
    }
}
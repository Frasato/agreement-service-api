package com.frasato.user_service.infra.web.controller;

import com.frasato.user_service.application.dto.LoginResult;
import com.frasato.user_service.application.dto.UserDetailResult;
import com.frasato.user_service.application.usecases.*;
import com.frasato.user_service.domain.model.User;
import com.frasato.user_service.infra.web.dto.AddConsortiumOnUserDto;
import com.frasato.user_service.infra.web.dto.UserLoginRequestDto;
import com.frasato.user_service.infra.web.dto.UserLoginResponseDto;
import com.frasato.user_service.infra.web.dto.UserRegisterRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final RegisterUserUseCase registerUserUseCase;
    private final LoginUserUseCase loginUserUseCase;
    private final AddFavoriteConsortiumUseCase favoriteConsortiumUseCase;
    private final UserDetailsUseCase userDetailsUseCase;
    private final ListOfUsersUseCase listOfUsersUseCase;

    public UserController(RegisterUserUseCase registerUserUseCase, LoginUserUseCase loginUserUseCase, AddFavoriteConsortiumUseCase favoriteConsortiumUseCase, UserDetailsUseCase userDetailsUseCase, ListOfUsersUseCase listOfUsersUseCase){
        this.registerUserUseCase = registerUserUseCase;
        this.loginUserUseCase = loginUserUseCase;
        this.favoriteConsortiumUseCase = favoriteConsortiumUseCase;
        this.userDetailsUseCase = userDetailsUseCase;
        this.listOfUsersUseCase = listOfUsersUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegisterRequestDto requestDto){
        User userDomain = new User(
                null,
                requestDto.name(),
                requestDto.phone(),
                requestDto.document(),
                requestDto.address(),
                requestDto.password(),
                "USER"
        );

        registerUserUseCase.register(userDomain);
        return ResponseEntity.status(HttpStatus.CREATED).body("User register success!");
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> login(@RequestBody UserLoginRequestDto loginRequestDto){
        LoginResult response = loginUserUseCase.login(loginRequestDto.document(), loginRequestDto.password());
        return ResponseEntity.status(HttpStatus.OK).body(new UserLoginResponseDto(response.id(), response.token()));
    }

    @PatchMapping("/add/consortium")
    public ResponseEntity<?> addConsortiumOnUser(@RequestBody AddConsortiumOnUserDto addDto){
        favoriteConsortiumUseCase.add(addDto.userId(), addDto.consortiumId());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<UserDetailResult> getUserDetails(@PathVariable("id") String id){
        return ResponseEntity.status(HttpStatus.OK).body(userDetailsUseCase.get(id));
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(listOfUsersUseCase.listAllUsers());
    }
}
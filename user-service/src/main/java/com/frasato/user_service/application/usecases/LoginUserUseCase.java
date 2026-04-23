package com.frasato.user_service.application.usecases;

import com.frasato.user_service.application.dto.LoginResult;
import com.frasato.user_service.domain.model.User;
import com.frasato.user_service.domain.repository.UserRepository;
import com.frasato.user_service.domain.service.TokenService;
import org.springframework.security.crypto.password.PasswordEncoder;

public class LoginUserUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public LoginUserUseCase(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenService tokenService){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    public LoginResult login(String document, String password){
        if(document.isBlank()) throw new RuntimeException("");
        User user = userRepository.findUserByDocument(document)
                .filter(foundedUser -> passwordEncoder.matches(password, foundedUser.getPassword()))
                .orElseThrow(() -> new RuntimeException("Document or Password was wrong"));

        String token = tokenService.generateToken(user);
        return new LoginResult(token, user.getId());
    }
}

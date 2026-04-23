package com.frasato.user_service.application.usecases;

import com.frasato.user_service.domain.model.User;
import com.frasato.user_service.domain.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

public class LoginUserUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginUserUseCase(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User login(String document, String password){
        if(document.isBlank()) throw new RuntimeException("");
        return userRepository.findUserByDocument(document)
                .filter(user -> passwordEncoder.matches(password, user.getPassword()))
                .orElseThrow(() -> new RuntimeException("Document or Password was wrong"));
    }
}

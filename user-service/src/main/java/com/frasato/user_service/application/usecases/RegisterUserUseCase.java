package com.frasato.user_service.application.usecases;

import com.frasato.user_service.domain.model.User;
import com.frasato.user_service.domain.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Optional;

public class RegisterUserUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    public RegisterUserUseCase(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(User user){

        Optional<User> foundedUser = userRepository.findUserByDocument(user.getDocument());
        if(foundedUser.isPresent()) throw new RuntimeException("User already registered");

        user.validateRole();
        user.validateDocument();
        user.validateName();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setConsortiumIds(new ArrayList<>());
        return userRepository.saveUser(user);
    }
}

package com.frasato.user_service.infra.config;

import com.frasato.user_service.application.usecases.LoginUserUseCase;
import com.frasato.user_service.application.usecases.RegisterUserUseCase;
import com.frasato.user_service.domain.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RegisterUserUseCase registerUserUseCase(UserRepository userRepository, PasswordEncoder passwordEncoder){
        return new RegisterUserUseCase(userRepository, passwordEncoder);
    }
    @Bean
    public LoginUserUseCase loginUserUseCase(UserRepository userRepository, PasswordEncoder passwordEncoder){
        return new LoginUserUseCase(userRepository, passwordEncoder);
    }
}

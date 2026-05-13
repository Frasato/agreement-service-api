package com.frasato.user_service.infra.config;

import com.frasato.user_service.application.usecases.AddFavoriteConsortiumUseCase;
import com.frasato.user_service.application.usecases.LoginUserUseCase;
import com.frasato.user_service.application.usecases.RegisterUserUseCase;
import com.frasato.user_service.application.usecases.UserDetailsUseCase;
import com.frasato.user_service.domain.repository.ConsortiumIntegrationRepository;
import com.frasato.user_service.domain.repository.UserRepository;
import com.frasato.user_service.domain.service.TokenService;
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
    public LoginUserUseCase loginUserUseCase(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenService tokenService){
        return new LoginUserUseCase(userRepository, passwordEncoder, tokenService);
    }
    @Bean
    public AddFavoriteConsortiumUseCase favoriteConsortiumUseCase(UserRepository userRepository){
        return new AddFavoriteConsortiumUseCase(userRepository);
    }
    @Bean
    public UserDetailsUseCase detailsUseCase(ConsortiumIntegrationRepository integrationRepository, UserRepository userRepository){
        return new UserDetailsUseCase(integrationRepository, userRepository);
    }
}

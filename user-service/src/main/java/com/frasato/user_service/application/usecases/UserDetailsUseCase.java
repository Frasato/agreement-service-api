package com.frasato.user_service.application.usecases;

import com.frasato.user_service.application.dto.UserDetailResult;
import com.frasato.user_service.domain.model.User;
import com.frasato.user_service.domain.repository.ConsortiumIntegrationRepository;
import com.frasato.user_service.domain.repository.UserRepository;

public class UserDetailsUseCase {

    private final ConsortiumIntegrationRepository consortiumIntegrationRepository;
    private final UserRepository userRepository;

    public UserDetailsUseCase(ConsortiumIntegrationRepository consortiumIntegrationRepository, UserRepository userRepository) {
        this.consortiumIntegrationRepository = consortiumIntegrationRepository;
        this.userRepository = userRepository;
    }

    public UserDetailResult get(String userId){
        User user = userRepository.findById(userId);

        var consortiums = consortiumIntegrationRepository.fetchConsortiumDetails(user.getConsortiumIds());

        return new UserDetailResult(
                user.getName(),
                user.getPhone(),
                user.getDocument(),
                user.getAddress(),
                consortiums
        );
    }
}

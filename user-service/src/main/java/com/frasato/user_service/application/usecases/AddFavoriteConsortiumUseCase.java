package com.frasato.user_service.application.usecases;

import com.frasato.user_service.domain.repository.UserRepository;

public class AddFavoriteConsortiumUseCase {
    private final UserRepository userRepository;

    public AddFavoriteConsortiumUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void add(String userId, String consortiumId){
        if(userId.isEmpty() || consortiumId.isBlank()){
            throw new RuntimeException("User id or Consortium id can't be empty");
        }
        userRepository.saveConsortiumOnUser(userId, consortiumId);
    }
}

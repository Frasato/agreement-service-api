package com.frasato.user_service.application.usecases;

import com.frasato.user_service.domain.model.User;
import com.frasato.user_service.domain.repository.UserRepository;
import com.frasato.user_service.infra.exception.ConsortiumAlreadyExistException;
import com.frasato.user_service.infra.exception.MissingParamsException;
import java.util.List;

public class AddFavoriteConsortiumUseCase {
    private final UserRepository userRepository;

    public AddFavoriteConsortiumUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User add(String userId, String consortiumId){
        if(userId.isEmpty() || consortiumId.isBlank()){
            throw new MissingParamsException("User ID or Consortium ID");
        }

        User user = userRepository.findById(userId);
        List<String> idList = user.getConsortiumIds();

        for(String id : idList){
            if(id.equals(consortiumId)) throw new ConsortiumAlreadyExistException(userId);
        }

        idList.add(consortiumId);
        user.setConsortiumIds(idList);

        return userRepository.saveConsortiumOnUser(user);
    }
}

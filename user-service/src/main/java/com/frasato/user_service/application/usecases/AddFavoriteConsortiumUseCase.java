package com.frasato.user_service.application.usecases;

import com.frasato.user_service.domain.model.User;
import com.frasato.user_service.domain.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class AddFavoriteConsortiumUseCase {
    private final UserRepository userRepository;

    public AddFavoriteConsortiumUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void add(String userId, String consortiumId){
        if(userId.isEmpty() || consortiumId.isBlank()){
            throw new RuntimeException("User id or Consortium id can't be empty");
        }

        User user = userRepository.findById(userId);
        List<String> idList = user.getConsortiumIds();

        if(idList == null) idList = new ArrayList<>();

        for(String id : idList){
            if(id.equals(consortiumId)) throw new RuntimeException("Consortium already exist in user: " + userId);
        }

        idList.add(consortiumId);
        user.setConsortiumIds(idList);

        userRepository.saveConsortiumOnUser(user);
    }
}

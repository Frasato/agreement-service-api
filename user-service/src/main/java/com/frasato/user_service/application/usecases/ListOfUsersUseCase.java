package com.frasato.user_service.application.usecases;

import com.frasato.user_service.domain.model.User;
import com.frasato.user_service.domain.repository.UserRepository;
import java.util.List;

public class ListOfUsersUseCase {

    private final UserRepository userRepository;

    public ListOfUsersUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> listAllUsers(){
        return userRepository.allUsers();
    }
}

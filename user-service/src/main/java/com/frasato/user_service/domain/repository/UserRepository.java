package com.frasato.user_service.domain.repository;

import com.frasato.user_service.domain.model.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User saveUser(User user);
    Optional<User> findUserByDocument(String document);
    User saveConsortiumOnUser(User user);
    User findById(String id);
    List<User> allUsers();
}

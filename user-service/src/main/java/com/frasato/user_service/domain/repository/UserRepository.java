package com.frasato.user_service.domain.repository;

import com.frasato.user_service.domain.model.User;

import java.util.Optional;

public interface UserRepository {
    User saveUser(User user);
    Optional<User> findUserByDocument(String document);
    void saveConsortiumOnUser(User user);
    User findById(String id);
}

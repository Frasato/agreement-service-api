package com.frasato.user_service.domain.repository;

import com.frasato.user_service.domain.model.User;

import java.util.Optional;

public interface UserRepository {
    void saveUser(User user);
    Optional<User> findUserByDocument(String document);
}

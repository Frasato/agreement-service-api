package com.frasato.user_service.domain.service;

import com.frasato.user_service.domain.model.User;

public interface TokenService {
    String generateToken(User user);
    String validateToken(String token);
}
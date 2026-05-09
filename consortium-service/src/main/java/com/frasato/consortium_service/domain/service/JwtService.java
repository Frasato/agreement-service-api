package com.frasato.consortium_service.domain.service;

public interface JwtService {
    String validateToken(String token);
    String getRole(String token);
}
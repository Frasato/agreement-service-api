package com.frasato.user_service.application.dto;

public record LoginResult(
        String token,
        String id
){}

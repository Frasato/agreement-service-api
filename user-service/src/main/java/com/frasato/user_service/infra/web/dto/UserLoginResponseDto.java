package com.frasato.user_service.infra.web.dto;

public record UserLoginResponseDto(
        String id,
        String token
){}

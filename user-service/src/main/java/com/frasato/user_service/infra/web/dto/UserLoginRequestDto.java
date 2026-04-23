package com.frasato.user_service.infra.web.dto;

public record UserLoginRequestDto(
        String document,
        String password
){}

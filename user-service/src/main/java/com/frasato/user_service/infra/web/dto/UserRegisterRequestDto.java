package com.frasato.user_service.infra.web.dto;

public record UserRegisterRequestDto(
        String name,
        String phone,
        String document,
        String address,
        String password
){}
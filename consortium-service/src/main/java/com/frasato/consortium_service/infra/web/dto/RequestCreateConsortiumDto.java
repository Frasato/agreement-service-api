package com.frasato.consortium_service.infra.web.dto;

public record RequestCreateConsortiumDto(
        String userId,
        String name,
        String description,
        Integer price
){}
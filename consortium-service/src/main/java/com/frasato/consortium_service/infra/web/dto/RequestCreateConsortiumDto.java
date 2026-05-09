package com.frasato.consortium_service.infra.web.dto;

public record RequestCreateConsortiumDto(
        String name,
        String description,
        Integer price
){}
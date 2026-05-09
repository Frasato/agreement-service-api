package com.frasato.consortium_service.infra.web.dto;

public record ResponseConsortiumDto(
        String id,
        String name,
        String description,
        Integer price,
        Boolean active
){}
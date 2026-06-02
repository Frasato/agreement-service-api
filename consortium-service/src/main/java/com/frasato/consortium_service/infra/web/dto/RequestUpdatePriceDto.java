package com.frasato.consortium_service.infra.web.dto;

public record RequestUpdatePriceDto(
        String consortiumId,
        String userId,
        Integer price
){}

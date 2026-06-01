package com.frasato.consortium_service.application.dto;

public record HistoricProducerDto(
        String service,
        String change,
        String changerId
){}
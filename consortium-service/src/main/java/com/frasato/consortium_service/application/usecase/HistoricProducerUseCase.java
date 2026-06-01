package com.frasato.consortium_service.application.usecase;

import com.frasato.consortium_service.application.dto.HistoricProducerDto;
import org.springframework.amqp.core.AmqpTemplate;
import tools.jackson.databind.ObjectMapper;

public class HistoricProducerUseCase {
    private final AmqpTemplate amqpTemplate;
    private final ObjectMapper objectMapper;

    public HistoricProducerUseCase(AmqpTemplate amqpTemplate, ObjectMapper objectMapper){
        this.amqpTemplate = amqpTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendHistoric(HistoricProducerDto historic){
        try{
            amqpTemplate.convertAndSend(
                    "historic-request-exchange",
                    "historic-request-rout-key",
                    objectMapper.writeValueAsString(historic)
            );
        }catch(RuntimeException e){
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }
}

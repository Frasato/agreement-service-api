package com.frasato.consortium_service.infra.config;

import com.frasato.consortium_service.application.usecase.CreateConsortiumUseCase;
import com.frasato.consortium_service.application.usecase.FindOneConsortiumUseCase;
import com.frasato.consortium_service.application.usecase.HistoricProducerUseCase;
import com.frasato.consortium_service.application.usecase.ListConsortiumsUseCase;
import com.frasato.consortium_service.domain.repository.ConsortiumRepository;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tools.jackson.databind.ObjectMapper;

@Configuration
public class ConsortiumConfig {

    @Bean
    public CreateConsortiumUseCase createConsortiumUseCase(ConsortiumRepository repository, HistoricProducerUseCase historicProducerUseCase) {
        return new CreateConsortiumUseCase(repository, historicProducerUseCase);
    }

    @Bean
    public FindOneConsortiumUseCase findOneConsortiumUseCase(ConsortiumRepository repository) {
        return new FindOneConsortiumUseCase(repository);
    }

    @Bean
    public ListConsortiumsUseCase listConsortiumsUseCase(ConsortiumRepository repository) {
        return new ListConsortiumsUseCase(repository);
    }

    @Bean
    public HistoricProducerUseCase historicProducerUseCase(AmqpTemplate amqpTemplate, ObjectMapper objectMapper){
        return new HistoricProducerUseCase(amqpTemplate, objectMapper);
    }

}

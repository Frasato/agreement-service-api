package com.frasato.consortium_service.application.usecase;

import com.frasato.consortium_service.application.dto.HistoricProducerDto;
import com.frasato.consortium_service.domain.model.Consortium;
import com.frasato.consortium_service.domain.repository.ConsortiumRepository;

public class CreateConsortiumUseCase {

    private final ConsortiumRepository consortiumRepository;
    private final HistoricProducerUseCase historicProducerUseCase;

    public CreateConsortiumUseCase(ConsortiumRepository consortiumRepository, HistoricProducerUseCase historicProducerUseCase){
        this.consortiumRepository = consortiumRepository;
        this.historicProducerUseCase = historicProducerUseCase;
    }

    public Consortium createNewConsortium(String userId, String name, String description, int price){
        consortiumRepository.findConsortiumByName(name);

        Consortium consortium = new Consortium();
        consortium.setName(name);
        consortium.setDescription(description);
        consortium.setPrice(price);

        consortium.validateName();
        consortium.validatePrice();

        Consortium savedConsortium = consortiumRepository.saveConsortium(consortium);

        HistoricProducerDto historicDto = new HistoricProducerDto(
                "consortium-service",
                "Create new consortium: " + name,
                userId
        );
        historicProducerUseCase.sendHistoric(historicDto);

        return savedConsortium;
    }
}
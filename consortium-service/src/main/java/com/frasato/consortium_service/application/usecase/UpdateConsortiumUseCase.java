package com.frasato.consortium_service.application.usecase;

import com.frasato.consortium_service.application.dto.HistoricProducerDto;
import com.frasato.consortium_service.domain.model.Consortium;
import com.frasato.consortium_service.domain.repository.ConsortiumRepository;
import com.frasato.consortium_service.infra.exception.ConsortiumNotFoundException;
import com.frasato.consortium_service.infra.exception.MissingParamException;

public class UpdateConsortiumUseCase {
    private final ConsortiumRepository consortiumRepository;
    private final HistoricProducerUseCase historicProducerUseCase;

    public UpdateConsortiumUseCase(ConsortiumRepository consortiumRepository, HistoricProducerUseCase historicProducerUseCase){
        this.consortiumRepository = consortiumRepository;
        this.historicProducerUseCase = historicProducerUseCase;
    }

    public Consortium updatePrice(String userId, String id, int price){
        if(id.isEmpty()) throw new MissingParamException("ID");

        Consortium consortium = consortiumRepository.findConsortiumById(id);
        if(consortium == null) throw new ConsortiumNotFoundException(id);

        consortium.setPrice(price);
        Consortium savedConsortium = consortiumRepository.saveConsortium(consortium);

        HistoricProducerDto historicProducerDto = new HistoricProducerDto(
                "Consortium-service",
                "Change price: " + consortium.getPrice() + "to: " + savedConsortium.getPrice(),
                userId
        );
        historicProducerUseCase.sendHistoric(historicProducerDto);

        return savedConsortium;
    }
}

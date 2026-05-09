package com.frasato.consortium_service.application.usecase;

import com.frasato.consortium_service.domain.model.Consortium;
import com.frasato.consortium_service.domain.repository.ConsortiumRepository;

public class CreateConsortiumUseCase {

    private final ConsortiumRepository consortiumRepository;

    public CreateConsortiumUseCase(ConsortiumRepository consortiumRepository){
        this.consortiumRepository = consortiumRepository;
    }

    public Consortium createNewConsortium(Consortium consortium){
        consortiumRepository.findConsortiumByName(consortium.getName());

        consortium.validateName();
        consortium.validatePrice();
        return consortiumRepository.saveConsortium(consortium);
    }
}
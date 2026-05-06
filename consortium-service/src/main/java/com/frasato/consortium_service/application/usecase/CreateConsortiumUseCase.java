package com.frasato.consortium_service.application.usecase;

import com.frasato.consortium_service.domain.model.Consortium;
import com.frasato.consortium_service.domain.repository.ConsortiumRepository;
import java.util.Optional;

public class CreateConsortiumUseCase {

    private final ConsortiumRepository consortiumRepository;

    public CreateConsortiumUseCase(ConsortiumRepository consortiumRepository){
        this.consortiumRepository = consortiumRepository;
    }

    public Consortium createNewConsortium(Consortium consortium){
        Optional<Consortium> foundedConsortium = consortiumRepository.findConsortiumByName(consortium.getName());
        if(foundedConsortium.isPresent()) throw new RuntimeException("Consortium already exist");

        consortium.validateName();
        consortium.validatePrice();
        return consortiumRepository.saveConsortium(consortium);
    }
}
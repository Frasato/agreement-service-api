package com.frasato.consortium_service.application.usecase;

import com.frasato.consortium_service.domain.model.Consortium;
import com.frasato.consortium_service.domain.repository.ConsortiumRepository;

public class CreateConsortiumUseCase {

    private final ConsortiumRepository consortiumRepository;

    public CreateConsortiumUseCase(ConsortiumRepository consortiumRepository){
        this.consortiumRepository = consortiumRepository;
    }

    public Consortium createNewConsortium(String name, String description, int price){
        consortiumRepository.findConsortiumByName(name);

        Consortium consortium = new Consortium();
        consortium.setName(name);
        consortium.setDescription(description);
        consortium.setPrice(price);

        consortium.validateName();
        consortium.validatePrice();
        return consortiumRepository.saveConsortium(consortium);
    }
}
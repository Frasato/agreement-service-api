package com.frasato.consortium_service.application.usecase;

import com.frasato.consortium_service.domain.model.Consortium;
import com.frasato.consortium_service.domain.repository.ConsortiumRepository;

import java.util.List;

public class ListConsortiumsUseCase {
    private final ConsortiumRepository consortiumRepository;

    public ListConsortiumsUseCase(ConsortiumRepository consortiumRepository){
        this.consortiumRepository = consortiumRepository;
    }

    public List<Consortium> listAllConsortiums(){
        return consortiumRepository.getAllConsortium();
    }
}

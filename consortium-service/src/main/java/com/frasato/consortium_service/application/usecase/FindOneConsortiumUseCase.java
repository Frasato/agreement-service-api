package com.frasato.consortium_service.application.usecase;

import com.frasato.consortium_service.domain.model.Consortium;
import com.frasato.consortium_service.domain.repository.ConsortiumRepository;

import java.util.Optional;

public class FindOneConsortiumUseCase {

    private final ConsortiumRepository consortiumRepository;

    public FindOneConsortiumUseCase(ConsortiumRepository consortiumRepository){
        this.consortiumRepository = consortiumRepository;
    }

    public Consortium findOne(String id){
        if(id.isEmpty()) throw new RuntimeException("id can't be empty");

        Consortium foundedConsortium = consortiumRepository.findConsortiumById(id);
        return foundedConsortium;
    }
}

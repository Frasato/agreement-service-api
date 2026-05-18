package com.frasato.consortium_service.application.usecase;

import com.frasato.consortium_service.domain.model.Consortium;
import com.frasato.consortium_service.domain.repository.ConsortiumRepository;
import com.frasato.consortium_service.infra.exception.MissingParamException;

public class FindOneConsortiumUseCase {

    private final ConsortiumRepository consortiumRepository;

    public FindOneConsortiumUseCase(ConsortiumRepository consortiumRepository){
        this.consortiumRepository = consortiumRepository;
    }

    public Consortium findOne(String id){
        if(id.isEmpty()) throw new MissingParamException("ID");

        return consortiumRepository.findConsortiumById(id);
    }
}

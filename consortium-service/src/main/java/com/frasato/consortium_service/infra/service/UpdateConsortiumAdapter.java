package com.frasato.consortium_service.infra.service;

import com.frasato.consortium_service.application.usecase.UpdateConsortiumUseCase;
import com.frasato.consortium_service.domain.model.Consortium;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UpdateConsortiumAdapter {

    private final UpdateConsortiumUseCase updateConsortiumUseCase;

    public UpdateConsortiumAdapter(UpdateConsortiumUseCase updateConsortiumUseCase) {
        this.updateConsortiumUseCase = updateConsortiumUseCase;
    }

    @Transactional
    public Consortium updatePrice(String userId, String id, int price){
        return updateConsortiumUseCase.updatePrice(userId, id, price);
    }
}
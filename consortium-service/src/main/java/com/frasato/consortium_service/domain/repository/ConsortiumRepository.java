package com.frasato.consortium_service.domain.repository;

import com.frasato.consortium_service.domain.model.Consortium;
import java.util.List;
import java.util.Optional;

public interface ConsortiumRepository {
    Consortium saveConsortium(Consortium consortium);
    List<Consortium> getAllConsortium();
    Consortium findConsortiumById(String id);
    void findConsortiumByName(String name);
}

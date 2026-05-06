package com.frasato.consortium_service.infra.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface JpaConsortiumRepository extends JpaRepository<ConsortiumEntity, String> {
    Optional<ConsortiumEntity> findConsortiumByName(String name);
}

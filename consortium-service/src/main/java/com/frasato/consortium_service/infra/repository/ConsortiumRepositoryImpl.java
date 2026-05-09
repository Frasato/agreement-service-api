package com.frasato.consortium_service.infra.repository;

import com.frasato.consortium_service.domain.model.Consortium;
import com.frasato.consortium_service.domain.repository.ConsortiumRepository;
import com.frasato.consortium_service.infra.persistance.ConsortiumEntity;
import com.frasato.consortium_service.infra.persistance.JpaConsortiumRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConsortiumRepositoryImpl implements ConsortiumRepository {
    private JpaConsortiumRepository jpaRepository;

    public ConsortiumRepositoryImpl(JpaConsortiumRepository jpaRepository){
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Consortium saveConsortium(Consortium consortium) {
        ConsortiumEntity consortiumEntity = new ConsortiumEntity();
        consortiumEntity.setName(consortium.getName());
        consortiumEntity.setDescription(consortium.getDescription());
        consortiumEntity.setPrice(consortium.getPrice());
        consortiumEntity.setActive(true);

        ConsortiumEntity saved = jpaRepository.save(consortiumEntity);

        return new Consortium(
                saved.getId(),
                saved.getName(),
                saved.getDescription(),
                saved.getPrice(),
                saved.getActive(),
                saved.getIncludedAt()
        );
    }

    @Override
    public List<Consortium> getAllConsortium() {
        List<ConsortiumEntity> consortiumEntityList = jpaRepository.findAll();
        List<Consortium> consortiumsList = new ArrayList<>();

        for(ConsortiumEntity consortiumEntity : consortiumEntityList){
            Consortium consortium = new Consortium(
                    consortiumEntity.getId(),
                    consortiumEntity.getName(),
                    consortiumEntity.getDescription(),
                    consortiumEntity.getPrice(),
                    consortiumEntity.getActive(),
                    consortiumEntity.getIncludedAt()
            );
            consortiumsList.add(consortium);
        }

        return consortiumsList;
    }

    @Override
    public Consortium findConsortiumById(String id) {
        Optional<ConsortiumEntity> founded = jpaRepository.findById(id);
        if(founded.isEmpty()) throw new RuntimeException("Consortium not found on ID: " + id);

        ConsortiumEntity consortiumEntity = founded.get();

        return new Consortium(
                consortiumEntity.getId(),
                consortiumEntity.getName(),
                consortiumEntity.getDescription(),
                consortiumEntity.getPrice(),
                consortiumEntity.getActive(),
                consortiumEntity.getIncludedAt()
        );
    }

    @Override
    public void findConsortiumByName(String name) {
        Optional<ConsortiumEntity> founded = jpaRepository.findConsortiumByName(name);
        if(founded.isEmpty()) throw new RuntimeException("Consortium already exist");
    }
}

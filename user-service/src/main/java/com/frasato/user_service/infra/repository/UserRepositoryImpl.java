package com.frasato.user_service.infra.repository;

import com.frasato.user_service.domain.model.User;
import com.frasato.user_service.domain.repository.UserRepository;
import com.frasato.user_service.infra.exception.UserNotFoundException;
import com.frasato.user_service.infra.persistance.JpaUserRepository;
import com.frasato.user_service.infra.persistance.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    public UserRepositoryImpl(JpaUserRepository jpaUserRepository){
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public User saveUser(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(user.getName());
        userEntity.setAddress(user.getAddress());
        userEntity.setPhone(user.getPhone());
        userEntity.setDocument(user.getDocument());
        userEntity.setPassword(user.getPassword());
        userEntity.setRole(user.getRole());
        userEntity.setConsortiumIds(user.getConsortiumIds());

        UserEntity saved = jpaUserRepository.save(userEntity);

        return new User(
                saved.getId(),
                saved.getName(),
                saved.getPhone(),
                saved.getDocument(),
                saved.getAddress(),
                saved.getPassword(),
                saved.getRole()
        );
    }

    @Override
    public Optional<User> findUserByDocument(String document) {
        return jpaUserRepository.findByDocument(document)
                .map(entity -> new User(entity.getId(), entity.getName(), entity.getPhone(), entity.getDocument(), entity.getAddress(), entity.getPassword(), entity.getRole()));
    }

    @Override
    public User saveConsortiumOnUser(User user) {
        UserEntity founded = jpaUserRepository.findById(user.getId())
                .orElseThrow(() -> new UserNotFoundException(user.getId()));

        founded.setConsortiumIds(user.getConsortiumIds());

        UserEntity entity = jpaUserRepository.save(founded);
        return new User(
                entity.getId(),
                entity.getName(),
                entity.getPhone(),
                entity.getDocument(),
                entity.getAddress(),
                entity.getPassword(),
                entity.getRole(),
                entity.getConsortiumIds()
        );
    }

    @Override
    public User findById(String id) {
        Optional<UserEntity> founded = jpaUserRepository.findById(id);
        if(founded.isEmpty()) throw new UserNotFoundException(id);

        UserEntity entity = founded.get();
        return new User(
                entity.getId(),
                entity.getName(),
                entity.getPhone(),
                entity.getDocument(),
                entity.getAddress(),
                entity.getPassword(),
                entity.getRole(),
                entity.getConsortiumIds()
        );
    }

    @Override
    public List<User> allUsers() {
        List<UserEntity> entityList = jpaUserRepository.findAll();
        List<User> users = new ArrayList<>();
        entityList
                .forEach(entity -> users.add(
                        new User(
                                entity.getId(),
                                entity.getName(),
                                entity.getPhone(),
                                entity.getDocument(),
                                entity.getAddress(),
                                entity.getPassword(),
                                entity.getRole(),
                                entity.getConsortiumIds())
                        )
                );
        return users;
    }
}

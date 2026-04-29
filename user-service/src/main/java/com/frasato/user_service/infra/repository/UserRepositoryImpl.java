package com.frasato.user_service.infra.repository;

import com.frasato.user_service.domain.model.User;
import com.frasato.user_service.domain.repository.UserRepository;
import com.frasato.user_service.infra.persistance.JpaUserRepository;
import com.frasato.user_service.infra.persistance.UserEntity;
import org.springframework.stereotype.Repository;
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
                .map(entity -> {
                    return new User(entity.getId(), entity.getName(), entity.getPhone(), entity.getDocument(), entity.getAddress(), entity.getPassword(), entity.getRole());
                });
    }
}

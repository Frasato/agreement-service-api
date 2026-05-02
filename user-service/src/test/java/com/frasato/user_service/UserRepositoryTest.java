package com.frasato.user_service;

import com.frasato.user_service.domain.model.User;
import com.frasato.user_service.infra.repository.UserRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb",
        "spring.datasource.driverClassName=org.h2.Driver",
        "spring.datasource.username=sa",
        "spring.datasource.password=",
        "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
public class UserRepositoryTest {

    @Autowired
    private UserRepositoryImpl userRepository;

    @Test
    void shouldSaveUser(){
        User user = createUser();

        var saved = userRepository.saveUser(user);
        assertNotNull(saved.getId());
    }

    @Test
    void shouldFindUserByDocument(){
        User user = createUser();

        userRepository.saveUser(user);
        var founded = userRepository.findUserByDocument(user.getDocument());

        assertTrue(founded.isPresent());
        assertEquals(founded.get().getDocument(), user.getDocument());
    }

    @Test
    void shouldReturnEmptyWhenUserNotFound(){
        var result = userRepository.findUserByDocument("35621487896");
        assertTrue(result.isEmpty());
    }

    private User createUser(){
        return new User(
                null,
                "Gabriel Silva dos Santos",
                "17991568457",
                "96586565326",
                "Rua Aurora Forte Neves",
                "123_encoded",
                "ROLE_USER"
        );
    }
}
package com.epam.webchat.leonidivanov;

import com.epam.webchat.leonidivanov.datalayer.repository.CustomizedUsersJpaRepository;
import com.epam.webchat.leonidivanov.datalayer.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Optional;

@SpringBootTest
public class DemoSpringDataApplicationTests {
    @Autowired
    private CustomizedUsersJpaRepository customizedUsersCrudRepository;

    @Test
    @Transactional
    public void testGet() {
        Optional<User> userOptional = customizedUsersCrudRepository.findById(1L);
    }
}

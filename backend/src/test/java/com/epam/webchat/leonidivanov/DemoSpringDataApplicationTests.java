package com.epam.webchat.leonidivanov;

import com.epam.webchat.leonidivanov.datalayer.dao.CustomizedUsersCrudRepository;
import com.epam.webchat.leonidivanov.datalayer.enties.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Optional;

@SpringBootTest
public class DemoSpringDataApplicationTests {
    @Autowired
    private CustomizedUsersCrudRepository customizedUsersCrudRepository;

    @Test
    @Transactional
    public void testGet() {
        Optional<User> userOptional = customizedUsersCrudRepository.findById(1L);
    }
}

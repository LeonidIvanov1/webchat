package com.epam.webchat.leonidivanov.services;

import com.epam.webchat.leonidivanov.datalayer.dao.CustomizedUsersCrudRepository;
import com.epam.webchat.leonidivanov.datalayer.enties.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDataService {

    @Autowired
    private CustomizedUsersCrudRepository usersCrudRepository;

    public void testEmployeesCrudRepository() {
        Optional<User> userOptional = usersCrudRepository.findById(1L);
    }


}

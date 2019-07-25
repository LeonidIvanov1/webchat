package com.epam.webchat.leonidivanov.datalayer.dao;

import com.epam.webchat.leonidivanov.datalayer.enties.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomizedUsersCrudRepository extends CrudRepository<User, Long> {
}

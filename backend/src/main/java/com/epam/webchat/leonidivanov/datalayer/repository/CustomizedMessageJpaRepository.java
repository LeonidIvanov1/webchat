package com.epam.webchat.leonidivanov.datalayer.repository;

import com.epam.webchat.leonidivanov.datalayer.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomizedMessageJpaRepository extends JpaRepository<Message, Long> {
}

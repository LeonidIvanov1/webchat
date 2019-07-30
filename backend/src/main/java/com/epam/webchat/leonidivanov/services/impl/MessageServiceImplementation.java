package com.epam.webchat.leonidivanov.services.impl;

import com.epam.webchat.leonidivanov.datalayer.entity.Message;
import com.epam.webchat.leonidivanov.datalayer.repository.CustomizedMessageJpaRepository;
import com.epam.webchat.leonidivanov.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImplementation implements MessageService {

    @Autowired
    CustomizedMessageJpaRepository messageJpaRepository;

    /**
     * Add new message to data source
     *
     * @param message -- new message
     * @return added message
     */
    @Override
    public Message addMessage(Message message) {
        return messageJpaRepository.saveAndFlush(message);
    }

    /**
     * Returns all messages
     *
     * @return list of messages
     */
    @Override
    public List<Message> getAllMessages() {
        return messageJpaRepository.findAll();
    }
}

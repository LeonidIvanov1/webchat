package com.epam.webchat.leonidivanov.services.impl;

import com.epam.webchat.leonidivanov.datalayer.entity.Message;
import com.epam.webchat.leonidivanov.datalayer.repository.CustomizedMessageJpaRepository;
import com.epam.webchat.leonidivanov.services.MessageService;
import com.epam.webchat.leonidivanov.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImplementation implements MessageService {

    private final
    CustomizedMessageJpaRepository messageJpaRepository;
    private final
    UserService userService;

    public MessageServiceImplementation(CustomizedMessageJpaRepository messageJpaRepository, UserService userService) {
        this.messageJpaRepository = messageJpaRepository;
        this.userService = userService;
    }

    /**
     * Add new message to data source
     *
     * @param message @return added message
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

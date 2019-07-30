package com.epam.webchat.leonidivanov.controller;

import com.epam.webchat.leonidivanov.datalayer.entity.Message;
import com.epam.webchat.leonidivanov.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Class describes controller for work with Message entity
 */
@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    /**
     * Returns all messages
     *
     * @return messages list
     */
    @GetMapping("/list")
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    /**
     * Adds new message
     *
     * @param addingMessage -- new message
     * @return added message
     */
    @PostMapping
    public Message addMessage(@RequestBody Message addingMessage) {
        return messageService.addMessage(addingMessage);
    }


}

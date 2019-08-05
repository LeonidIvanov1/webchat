package com.epam.webchat.leonidivanov.services;


import com.epam.webchat.leonidivanov.datalayer.entity.Message;

import java.util.List;

/**
 * Describes methods to work with Message entity
 */
public interface MessageService {
    /**
     * Add new message to data source
     *
     * @param message -- message data
     * @return added message
     */
    Message addMessage(Message message);

    /**
     * Returns all messages
     *
     * @return list of messages
     * @param messageIndex
     */
    List<Message> getMessages(int messageIndex);
}

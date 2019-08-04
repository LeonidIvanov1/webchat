package com.epam.webchat.leonidivanov.controller;

import com.epam.webchat.leonidivanov.controller.dto.MessageDto;
import com.epam.webchat.leonidivanov.datalayer.entity.Message;
import com.epam.webchat.leonidivanov.services.MessageService;
import com.epam.webchat.leonidivanov.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class describes controller for work with Message entity
 */
@Slf4j
@RestController
@RequestMapping("/message")
public class MessageRestController {
    private final MessageService messageService;
    private final ModelMapper modelMapper;
    private final UserService userService;

    public MessageRestController(MessageService messageService,
                                 ModelMapper modelMapper, UserService userService) {
        this.messageService = messageService;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    /**
     * Returns all messages
     *
     * @return messages list
     */
    @Secured({"ROLE_ADMINISTRATOR", "ROLE_USER"})
    @GetMapping
    public List<MessageDto> getAllMessages() {
        log.debug("Trying to get all messages");
        return messageService.getAllMessages().stream().map(this::convertToDto)
                .collect(Collectors.toList());
    }

    /**
     * Adds new message
     *
     * @param addingMessage -- new message
     * @return added message
     */
    @Secured({"ROLE_ADMINISTRATOR", "ROLE_USER"})
    @PostMapping
    public MessageDto addMessage(@RequestBody MessageDto addingMessage) {
        log.debug("Trying to add new message by author {}", addingMessage.getId());
        return convertToDto(messageService.addMessage(convertToEntity(addingMessage)));
    }

    /**
     * Converts Message entity to Message DTO
     *
     * @param message -- user entity
     * @return user DTO
     */
    private MessageDto convertToDto(Message message) {
            MessageDto messageDto = modelMapper.map(message, MessageDto.class);
            messageDto.setAuthorFullName(message.getAuthor().getFullName());
            return messageDto;
    }

    /**
     * Converts message DTO to message entity
     *
     * @param message -- message DTO
     * @return user DTO
     */
    private Message convertToEntity(MessageDto message) {
        Message addingMessage = modelMapper.map(message, Message.class);
        addingMessage.setAuthor(userService.findById(message.getAuthorId()));
        return addingMessage;
    }


}

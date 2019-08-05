package com.epam.webchat.leonidivanov.controller;

import com.epam.webchat.leonidivanov.controller.dto.MessageDto;
import com.epam.webchat.leonidivanov.datalayer.entity.Message;
import com.epam.webchat.leonidivanov.services.MessageService;
import com.epam.webchat.leonidivanov.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Class describes controller for work with Message entity
 */
@Slf4j
@RestController
@RequestMapping("/message")
public class MessageRestController {
    private final Map<DeferredResult<List<MessageDto>>, Integer> chatRequests =
            new ConcurrentHashMap<>();

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
    public DeferredResult<List<MessageDto>> getAllMessages(
            @RequestParam int messageIndex) {
        log.debug("Trying to get all messages");
        final DeferredResult<List<MessageDto>> deferredResult =
                new DeferredResult<>(
                        null, Collections.emptyList());
        this.chatRequests.put(deferredResult, messageIndex);

        deferredResult.onCompletion(new Runnable() {
            @Override
            public void run() {
                chatRequests.remove(deferredResult);
            }
        });

        List<MessageDto> messages = messageService
                .getMessages(messageIndex)
                .stream().map(this::convertToDto)
                .collect(Collectors.toList());
        if (!messages.isEmpty()) {
            deferredResult.setResult(messages);
        }
        return deferredResult;
    }

    /**
     * Adds new message
     *
     * @param addingMessage -- new message
     * @return added message
     */
    @Secured({"ROLE_ADMINISTRATOR", "ROLE_USER"})
    @PostMapping
    public void addMessage(@RequestBody @Valid MessageDto addingMessage, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.warn("MessageDTO is not valid {}", addingMessage);
            throw new ValidationException("Message data is not valid");
        }
        log.info("Trying to add new message by author {}", addingMessage.getId());
        MessageDto messageDto = convertToDto(messageService.addMessage(convertToEntity(addingMessage)));
        for (Map.Entry<DeferredResult<List<MessageDto>>, Integer> entry : this.chatRequests.entrySet()) {
            List<MessageDto> messages = messageService
                    .getMessages(entry.getValue())
                    .stream().map(this::convertToDto)
                    .collect(Collectors.toList());
            entry.getKey().setResult(messages);
        }
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

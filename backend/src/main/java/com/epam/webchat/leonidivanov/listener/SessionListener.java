package com.epam.webchat.leonidivanov.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@Slf4j
@Component
public class SessionListener implements HttpSessionListener {
    /**
     * Notification that a session was created.
     * The default implementation is a NO-OP.
     *
     * @param se the notification event
     */
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        log.info("Session was created with ID {}", se.getSession().getId());
    }

    /**
     * Notification that a session is about to be invalidated.
     * The default implementation is a NO-OP.
     *
     * @param se the notification event
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        log.info("Session was destroyed with ID {}",  se.getSession().getId());
    }
}

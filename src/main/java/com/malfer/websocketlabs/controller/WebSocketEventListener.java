package com.malfer.websocketlabs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.malfer.websocketlabs.dto.Message;
import com.malfer.websocketlabs.dto.MessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class WebSocketEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketEventListener.class);

    @Autowired
    private SimpMessageSendingOperations sendingOperations;

    @EventListener
    public void handleWebSocketConnectListener(final SessionConnectedEvent event){
        LOGGER.info("New connection!");
    }

    @EventListener
    public void handleWebSocketDiscoinnectListener(final SessionDisconnectEvent event){
        LOGGER.info("Closing connection!");
        final StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        final String username = (String) headerAccessor.getSessionAttributes().get("username");
        final Message message = Message.builder()
                .type(MessageType.DISCONNECT)
                .time(new SimpleDateFormat("HH:mm").format(new Date()))
                .sender(username)
                .build();
        sendingOperations.convertAndSend("/topic/public",message);
    }
}

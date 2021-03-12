package com.malfer.websocketlabs.controller;


import com.malfer.websocketlabs.dto.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageController {

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public String send(Message message) throws Exception {
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        return new StringBuilder()
                .append(message.getFrom())
                .append(message.getText())
                .append(time)
                .toString();
    }
}

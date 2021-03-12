package com.malfer.websocketlabs.controller;


import com.malfer.websocketlabs.dto.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class MessageController {

    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public Message send(@Payload final Message message) {
        //String time = new SimpleDateFormat("HH:mm").format(new Date());
        return message;
    }

    @MessageMapping("/chat.newUser")
    @SendTo("/topic/public")
    public Message newUser(@Payload final Message message
                            , SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username",message.getSender());
        return message;
    }
}

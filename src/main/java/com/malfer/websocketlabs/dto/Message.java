package com.malfer.websocketlabs.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
public class Message {

    @Getter
    private MessageType type;

    @Getter
    private String content;

    @Getter
    private String sender;

    @Getter
    private String time;
}

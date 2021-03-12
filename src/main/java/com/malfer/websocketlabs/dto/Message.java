package com.malfer.websocketlabs.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class Message {

    @Getter
    private MessageType type;

    @Getter
    private String content;

    @Getter
    private String sender;

    @Getter
    @Setter
    private String time;
}

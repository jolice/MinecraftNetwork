package me.riguron.messaging.serialize;

import lombok.Value;

@Value
public class MessageData {

    private String type;
    private String content;
}

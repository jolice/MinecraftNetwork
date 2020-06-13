package com.github.jolice.system.dialog;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class PrivateMessageProjection {

    private String from;
    private String to;
    private String text;
    private LocalDateTime dateTime;

}

package io.riguron.system.dialog;

import lombok.Value;

import java.util.UUID;

@Value
public class PrivateMessage {

    private final UUID from;
    private final String toName;
    private final String text;
    private final String fromName;

}

package me.riguron.messaging.serialize;

import java.nio.charset.StandardCharsets;

public class MessageDataExtractor {

    /**
     * Extracts message type and body from the raw serialized string.
     *
     * @param payload serialized message with type prefix
     * @return object containing message type and data
     */
    public MessageData getMessageData(byte[] payload) {
        String dataString = new String(payload, StandardCharsets.UTF_8);
        int separator = dataString.indexOf(':');
        return new MessageData(dataString.substring(0, separator), dataString.substring(separator + 1, dataString.length()));
    }
}

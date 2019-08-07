package me.riguron.messaging.serialize;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import me.riguron.messaging.Message;


@RequiredArgsConstructor
public class MessageSerializer {

    private final Gson gson;

    /**
     * Serializes a message with accordance to the following format:
     * <p>
     * "Name of Java message type:json message body"
     *
     * @param message body of the message
     * @return serialized string
     */
    public byte[] serialize(Message message) {
        String json = gson.toJson(message);
        return (message.getClass().getSimpleName() + ":" + json).getBytes();
    }
}

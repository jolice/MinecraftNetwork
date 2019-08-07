package me.riguron.messaging.handler;

import java.lang.reflect.Type;

/**
 * Class responsible for handling incoming messages of a certain type.
 *
 * @param <T> type of the messages the handler handles
 */
public interface MessageHandler<T> {

    /**
     * Message handling logic.
     *
     * @param message body of message
     */
    void accept(T message);

    /**
     * Type of the message.
     *
     * @return message type.
     */
    Type getMessageType();
}

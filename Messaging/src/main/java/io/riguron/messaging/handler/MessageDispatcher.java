package io.riguron.messaging.handler;

import io.riguron.messaging.serialize.MessageData;
import io.riguron.messaging.serialize.MessageDataExtractor;
import io.riguron.messaging.serialize.MessageDeserializer;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Class responsible for passing incoming messages to appropriate handlers.
 */
@RequiredArgsConstructor
public class MessageDispatcher {

    private final Map<String, MessageHandler<?>> handlers = new HashMap<>();
    private final MessageDeserializer messageDeserializer;
    private final MessageDataExtractor messageDataExtractor;

    /**
     * Registers message handler
     *
     * @param handler message handlers.
     */
    public void addHandler(MessageHandler<?> handler) {
        this.handlers.put(typeName(handler.getMessageType()), handler);
    }

    /**
     * De-serializes incoming message and passes it to the appropriate handler.
     *
     * @param payload raw serialized message payload.
     * @throws IllegalArgumentException if there is no handler corresponding to the message type
     */
    public void dispatch(byte[] payload) {
        MessageData message = messageDataExtractor.getMessageData(payload);
        MessageHandler<?> messageHandler = handlers.get(message.getType());
        if (messageHandler == null) {
            throw new IllegalArgumentException("No handler registered for type " + message.getType());
        }
        messageHandler.accept(messageDeserializer.deserialize(message.getContent(), messageHandler.getMessageType()));
    }

    private String typeName(Type type) {
        String typeName = type.getTypeName();
        return typeName.substring(typeName.lastIndexOf('.') + 1);
    }

}

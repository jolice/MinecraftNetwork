package io.riguron.messaging.handler;

import io.riguron.messaging.serialize.MessageData;
import io.riguron.messaging.serialize.MessageDataExtractor;
import io.riguron.messaging.serialize.MessageDeserializer;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MessageDispatcherTest {

    @Test
    public void addHandler() {
        MessageDeserializer deserializer = mock(MessageDeserializer.class);
        MessageDataExtractor extractor = mock(MessageDataExtractor.class);
        MessageDispatcher dispatcher = new MessageDispatcher(deserializer, extractor);

        MessageHandler<String> handler = mock(MessageHandler.class);
        when(handler.getMessageType()).thenReturn(String.class);

        dispatcher.addHandler(handler);

        byte[] payload = new byte[0];

        when(extractor.getMessageData(eq(payload))).thenReturn(
                new MessageData(
                        "String", "content"
                )
        );

       when( deserializer.deserialize(eq("content"), eq(String.class))).thenReturn("XXX");

       dispatcher.dispatch(payload);

       verify(handler).accept(eq("XXX"));
    }
}
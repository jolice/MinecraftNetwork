package io.riguron.messaging.serialize;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MessageDataExtractorTest {

    @Test
    public void getMessageData() {
        String src = "io.riguron.type.T:{some serialized object}";
        MessageDataExtractor extractor = new MessageDataExtractor();
        MessageData messageData = extractor.getMessageData(src.getBytes());
        assertEquals("io.riguron.type.T",messageData.getType());
        assertEquals("{some serialized object}", messageData.getContent());
    }
}
package me.riguron.messaging.serialize;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MessageDataExtractorTest {

    @Test
    public void getMessageData() {
        String src = "me.riguron.type.T:{some serialized object}";
        MessageDataExtractor extractor = new MessageDataExtractor();
        MessageData messageData = extractor.getMessageData(src.getBytes());
        assertEquals("me.riguron.type.T",messageData.getType());
        assertEquals("{some serialized object}", messageData.getContent());
    }
}
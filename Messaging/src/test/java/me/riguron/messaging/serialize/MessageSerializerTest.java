package me.riguron.messaging.serialize;

import com.google.gson.Gson;
import me.riguron.messaging.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.legacy.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Class.class, Gson.class})
public class MessageSerializerTest {

    @Test
    public void serialize() {

        Gson gson = mock(Gson.class);
        Message message = mock(Message.class);
        String className = message.getClass().getSimpleName();
        when(gson.toJson(eq(message))).thenReturn("{json}");
        MessageSerializer serializer = new MessageSerializer(gson);

        byte[] serialize = serializer.serialize(message);
        assertEquals(className + ":{json}", new String(serialize));
    }
}
package me.riguron.messaging.amqp;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import me.riguron.messaging.Message;
import me.riguron.messaging.MessagingException;
import me.riguron.messaging.MessagingService;
import me.riguron.messaging.serialize.MessageSerializer;

import java.io.IOException;

@RequiredArgsConstructor
public class AMQPMessagingService implements MessagingService {

    private final Channel channel;
    private final MessageSerializer messageSerializer;

    @Override
    public void send(Message message, String receiver)  {
        try {
            channel.basicPublish("", receiver, new AMQP.BasicProperties.Builder().build(), messageSerializer.serialize(message));
        } catch (IOException e) {
            throw new MessagingException(e);
        }
    }


}

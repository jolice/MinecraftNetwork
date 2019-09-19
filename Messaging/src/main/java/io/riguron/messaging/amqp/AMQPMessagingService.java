package io.riguron.messaging.amqp;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import io.riguron.messaging.serialize.MessageSerializer;
import lombok.RequiredArgsConstructor;
import io.riguron.messaging.Message;
import io.riguron.messaging.MessagingException;
import io.riguron.messaging.MessagingService;

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

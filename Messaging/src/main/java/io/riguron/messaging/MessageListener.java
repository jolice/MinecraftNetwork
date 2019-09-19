package io.riguron.messaging;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import io.riguron.messaging.handler.MessageDispatcher;

import java.io.IOException;

public class MessageListener extends DefaultConsumer {

    private MessageDispatcher messageDispatcher;

    public MessageListener(Channel channel, MessageDispatcher messageDispatcher) {
        super(channel);
        this.messageDispatcher = messageDispatcher;
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        messageDispatcher.dispatch(body);
    }
}

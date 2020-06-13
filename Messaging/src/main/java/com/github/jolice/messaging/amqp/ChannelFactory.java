package io.riguron.messaging.amqp;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.RequiredArgsConstructor;
import com.github.jolice.common.shutdown.ShutdownHooks;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Factory responsible for creating AMQP messaging channels.
 */
@RequiredArgsConstructor
public class ChannelFactory {

    private final ConnectionFactory connectionFactory;
    private final ShutdownHooks shutdownHooks;

    /**
     * Creates new RabbitMQ channel and adds a shutdown hooks
     * that terminates channel when server shuts down.
     *
     * @return created channel instance
     */
    public Channel newChannel() {
        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            shutdownHooks.addHook(() -> close(connection));
            shutdownHooks.addHook(() -> close(channel));
            return channel;
        } catch (IOException | TimeoutException e) {
            throw new AMQPException(e);
        }
    }

    private void close(AutoCloseable autoCloseable) {
        try {
            autoCloseable.close();
        } catch (Exception e) {
            throw new AMQPException(e);
        }
    }
}

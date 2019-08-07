package me.riguron.bungee;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import lombok.RequiredArgsConstructor;
import me.riguron.server.ServerName;
import me.riguron.system.task.startup.PostLoadTask;
import me.riguron.messaging.MessagingChannels;
import me.riguron.messaging.amqp.AMQPException;

import java.io.IOException;

/**
 * Task responsible for declaring AMQP channel for BungeeCord
 */
@RequiredArgsConstructor
public class ProxyChannelDeclaration implements PostLoadTask {

    private final DefaultConsumer messageListener;
    private final ServerName serverName;
    private final Channel channel;

    @Override
    public void run() {
        try {
            channel.queueDeclare(MessagingChannels.PROXY, false, false, false, null);
            channel.queueDeclare(serverName.get(), false, false, false, null);
            channel.basicConsume(MessagingChannels.PROXY, true, messageListener);
            channel.basicConsume(serverName.get(), true, messageListener);
        } catch (IOException e) {
            throw new AMQPException(e);
        }
    }

}

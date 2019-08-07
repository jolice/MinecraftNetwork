package me.riguron.messaging.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import me.riguron.common.shutdown.ShutdownHooks;
import me.riguron.messaging.amqp.ChannelFactory;

import java.util.concurrent.TimeUnit;

public class RabbitModule extends AbstractModule {

    @Provides
    @Singleton
    public ConnectionFactory connectionFactory() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setAutomaticRecoveryEnabled(true);
        factory.setNetworkRecoveryInterval(TimeUnit.SECONDS.toMillis(1));
        return factory;
    }

    @Provides
    @Singleton
    public ChannelFactory channelFactory(ConnectionFactory connectionFactory, ShutdownHooks shutdownHooks) {
        return new ChannelFactory(connectionFactory, shutdownHooks);
    }


    @Provides
    @Singleton
    @Named("Write")
    public Channel writeChannel(ChannelFactory channelFactory) {
        return channelFactory.newChannel();
    }

}

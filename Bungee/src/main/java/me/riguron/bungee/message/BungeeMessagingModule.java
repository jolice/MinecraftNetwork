package me.riguron.bungee.message;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.rabbitmq.client.Channel;
import me.riguron.messaging.MessageListener;
import me.riguron.messaging.amqp.ChannelFactory;
import me.riguron.messaging.handler.MessageDispatcher;
import me.riguron.messaging.handler.MessageHandler;
import me.riguron.messaging.serialize.MessageDataExtractor;
import me.riguron.messaging.serialize.MessageDeserializer;

import java.util.Set;

public class BungeeMessagingModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new MessageHandlerModule());
    }

    @Singleton
    @Provides
    public MessageDispatcher messageDispatcher(MessageDeserializer messageDeserializer, Set<MessageHandler<?>> handlers, MessageDataExtractor messageDataExtractor) {
        MessageDispatcher messageDispatcher = new MessageDispatcher(messageDeserializer, messageDataExtractor);
        handlers.forEach(messageDispatcher::addHandler);
        return messageDispatcher;
    }

    @Provides
    @Singleton
    public MessageListener messageListener(@Named("Read") Channel channel, MessageDispatcher messageDispatcher) {
        return new MessageListener(channel, messageDispatcher);
    }

    @Provides
    @Singleton
    @Named("Read")
    public Channel readChannel(ChannelFactory channelFactory) {
        return channelFactory.newChannel();
    }

}

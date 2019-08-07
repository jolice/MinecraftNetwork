package me.riguron.messaging;

import com.google.gson.Gson;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.rabbitmq.client.Channel;
import me.riguron.messaging.amqp.AMQPMessagingService;
import me.riguron.messaging.module.RabbitModule;
import me.riguron.messaging.serialize.MessageDataExtractor;
import me.riguron.messaging.serialize.MessageDeserializer;
import me.riguron.messaging.serialize.MessageSerializer;

public class MessagingModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new RabbitModule());
    }

    @Singleton
    @Provides
    private MessagingService messagingService(@Named("Write") Channel channel, MessageSerializer messageSerializer) {
        return new AMQPMessagingService(channel, messageSerializer);
    }


    @Singleton
    @Provides
    public MessageSerializer messageSerializer(@Named("MessagingGson") Gson gson) {
        return new MessageSerializer(gson);
    }

    @Provides
    @Singleton
    public MessageDeserializer messageDeserializer(Gson gson) {
        return new MessageDeserializer(gson);
    }

    @Provides
    @Singleton
    public MessageDataExtractor messageDataExtractor() {
        return new MessageDataExtractor();
    }


    @Provides
    @Singleton
    @Named("MessagingGson")
    public Gson gson() {
        return new Gson();
    }


}

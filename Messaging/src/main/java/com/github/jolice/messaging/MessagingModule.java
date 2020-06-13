package io.riguron.messaging;

import com.google.gson.Gson;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.rabbitmq.client.Channel;
import io.riguron.messaging.amqp.AMQPMessagingService;
import io.riguron.messaging.serialize.MessageDataExtractor;
import io.riguron.messaging.serialize.MessageDeserializer;
import io.riguron.messaging.serialize.MessageSerializer;
import io.riguron.messaging.module.RabbitModule;

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

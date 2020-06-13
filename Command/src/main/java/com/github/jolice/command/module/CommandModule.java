package io.riguron.command.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import io.riguron.command.engine.CommandExecutor;
import io.riguron.command.engine.CommandFrontController;
import io.riguron.command.engine.CommandOptionsValidator;
import io.riguron.command.nested.NestedCommandFactory;
import io.riguron.command.sender.DefaultSenderFactory;
import io.riguron.command.sender.SenderFactory;
import io.riguron.command.engine.CommandExecutor;
import io.riguron.command.engine.CommandOptionsValidator;
import io.riguron.command.engine.CommandFrontController;
import io.riguron.command.nested.NestedCommandFactory;
import io.riguron.command.repository.CommandRepository;
import io.riguron.command.repository.VirtualCommandRepository;
import io.riguron.command.sender.DefaultSenderFactory;
import io.riguron.command.sender.SenderFactory;
import io.riguron.system.internalization.InternalizationService;
import io.riguron.system.internalization.SendMessage;
import io.riguron.system.player.PlayerProfileRepository;

public class CommandModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(CommandPostProcessor.class).asEagerSingleton();
    }

    @Provides
    @Singleton
    public CommandRepository commandRepository() {
        return new VirtualCommandRepository();
    }

    @Provides
    @Singleton
    public SenderFactory senderFactory(PlayerProfileRepository playerProfileRepository, @Named("ConsoleSendMessage") SendMessage console, @Named("PlayerSendMessage") SendMessage player) {
        return new DefaultSenderFactory(playerProfileRepository, console, player);
    }

    @Provides
    @Singleton
    public CommandExecutor executor(SendMessage sendMessage, CommandOptionsValidator validator) {
        return new CommandExecutor(sendMessage, validator);
    }

    @Provides
    @Singleton
    public CommandOptionsValidator commandOptionsValidator() {
        return new CommandOptionsValidator();
    }

    @Provides
    @Singleton
    public CommandFrontController commandFrontController(CommandExecutor executor, CommandRepository repository, SenderFactory senderFactory) {
        return new CommandFrontController(repository, executor, senderFactory);
    }

    @Provides
    @Singleton
    public NestedCommandFactory nestedCommandFactory(InternalizationService internalizationService, CommandExecutor commandExecutor) {
        return new NestedCommandFactory(internalizationService, commandExecutor);
    }


}

package me.riguron.command.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import me.riguron.command.engine.CommandExecutor;
import me.riguron.command.engine.CommandOptionsValidator;
import me.riguron.command.engine.CommandFrontController;
import me.riguron.command.nested.NestedCommandFactory;
import me.riguron.command.repository.CommandRepository;
import me.riguron.command.repository.VirtualCommandRepository;
import me.riguron.command.sender.DefaultSenderFactory;
import me.riguron.command.sender.SenderFactory;
import me.riguron.system.internalization.InternalizationService;
import me.riguron.system.internalization.SendMessage;
import me.riguron.system.player.PlayerProfileRepository;

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

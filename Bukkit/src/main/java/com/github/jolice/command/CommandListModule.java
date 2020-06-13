package com.github.jolice.command;

import com.github.jolice.command.alert.AlertCommand;
import com.github.jolice.command.alert.AlertCommandOptions;
import com.github.jolice.command.find.FindCommand;
import com.github.jolice.command.find.FindCommandOptions;
import com.github.jolice.command.party.PartyCommandModule;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.multibindings.ProvidesIntoSet;
import io.riguron.command.repository.CommandRegistration;
import io.riguron.messaging.MessagingService;
import io.riguron.server.repository.PlayerRepository;
import io.riguron.system.task.TaskFactory;

public class CommandListModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new PartyCommandModule());
    }

    @ProvidesIntoSet
    @Singleton
    public CommandRegistration alert(MessagingService messagingService, TaskFactory taskFactory) {
        return new CommandRegistration(new AlertCommand(messagingService, taskFactory), new AlertCommandOptions());
    }

    @ProvidesIntoSet
    @Singleton
    public CommandRegistration find(PlayerRepository repository, TaskFactory taskFactory) {
        return new CommandRegistration(new FindCommand(repository, taskFactory), new FindCommandOptions());
    }
}

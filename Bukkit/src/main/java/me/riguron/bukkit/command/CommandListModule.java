package me.riguron.bukkit.command;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.multibindings.ProvidesIntoSet;
import me.riguron.bukkit.command.alert.AlertCommand;
import me.riguron.bukkit.command.alert.AlertCommandOptions;
import me.riguron.bukkit.command.find.FindCommand;
import me.riguron.bukkit.command.find.FindCommandOptions;
import me.riguron.bukkit.command.party.PartyCommandModule;
import me.riguron.command.repository.CommandRegistration;
import me.riguron.messaging.MessagingService;
import me.riguron.server.repository.PlayerRepository;
import me.riguron.system.task.TaskFactory;

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

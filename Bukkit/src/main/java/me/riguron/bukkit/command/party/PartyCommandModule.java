package me.riguron.bukkit.command.party;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.multibindings.ProvidesIntoMap;
import com.google.inject.multibindings.ProvidesIntoSet;
import com.google.inject.multibindings.StringMapKey;
import com.google.inject.name.Named;
import me.riguron.bukkit.command.party.accept.AcceptCommandOptions;
import me.riguron.bukkit.command.party.accept.PartyAcceptCommand;
import me.riguron.bukkit.command.party.chat.ChatCommandOptions;
import me.riguron.bukkit.command.party.chat.PartyChatCommand;
import me.riguron.bukkit.command.party.create.CreateCommandOptions;
import me.riguron.bukkit.command.party.create.PartyCreateCommand;
import me.riguron.bukkit.command.party.disband.DisbandCommandOptions;
import me.riguron.bukkit.command.party.disband.PartyDisbandCommand;
import me.riguron.bukkit.command.party.invite.InviteCommandOptions;
import me.riguron.bukkit.command.party.invite.PartyInviteCommand;
import me.riguron.bukkit.command.party.remove.PartyRemoveCommand;
import me.riguron.bukkit.command.party.remove.RemoveCommandOptions;
import me.riguron.command.nested.NestedCommandFactory;
import me.riguron.command.repository.CommandRegistration;
import me.riguron.system.internalization.SendMessage;
import me.riguron.system.party.PartyService;
import me.riguron.system.task.TaskFactory;

import java.util.Map;

public class PartyCommandModule extends AbstractModule {

    @ProvidesIntoSet
    @Singleton
    public CommandRegistration partyCommandRegistration(@Named("PartySubCommands") Map<String, CommandRegistration> subCommands, NestedCommandFactory nestedCommandFactory) {
        return new CommandRegistration(nestedCommandFactory.newNestedCommand(subCommands), new PartyCommandOptions());
    }

    @Provides
    @Singleton
    private PartySubCommandFactory factory(PartyService partyService, TaskFactory taskFactory, SendMessage sendMessage) {
        return new PartySubCommandFactory(partyService, taskFactory, sendMessage);
    }

    @ProvidesIntoMap
    @Singleton
    @Named("PartySubCommands")
    @StringMapKey("accept")
    private CommandRegistration partyAcceptCommand(PartySubCommandFactory factory) {
        return factory.createCommand(PartyAcceptCommand::new, new AcceptCommandOptions());
    }

    @ProvidesIntoMap
    @Singleton
    @Named("PartySubCommands")
    @StringMapKey("create")
    private CommandRegistration partyCreateCommand(PartySubCommandFactory factory) {
        return factory.createCommand(PartyCreateCommand::new, new CreateCommandOptions());
    }

    @ProvidesIntoMap
    @Singleton
    @Named("PartySubCommands")
    @StringMapKey("disband")
    private CommandRegistration partyDisbandCommand(PartySubCommandFactory factory) {
        return factory.createCommand(PartyDisbandCommand::new, new DisbandCommandOptions());
    }

    @ProvidesIntoMap
    @Singleton
    @Named("PartySubCommands")
    @StringMapKey("invite")
    private CommandRegistration partyInviteCommand(PartySubCommandFactory factory) {
        return factory.createCommand(PartyInviteCommand::new, new InviteCommandOptions());
    }

    @ProvidesIntoMap
    @Singleton
    @Named("PartySubCommands")
    @StringMapKey("remove")
    private CommandRegistration partyRemoveCommand(PartySubCommandFactory factory) {
        return factory.createCommand(PartyRemoveCommand::new, new RemoveCommandOptions());
    }

    @ProvidesIntoMap
    @Singleton
    @Named("PartySubCommands")
    @StringMapKey("chat")
    private CommandRegistration commandRegistration(PartyService partyService, SendMessage sendMessage, TaskFactory taskFactory) {
        return new CommandRegistration(new PartyChatCommand(partyService, sendMessage, taskFactory), new ChatCommandOptions());
    }

}

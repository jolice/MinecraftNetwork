package me.riguron.bukkit.command.party;

import lombok.RequiredArgsConstructor;
import me.riguron.command.base.CommandOptions;
import me.riguron.command.repository.CommandRegistration;
import me.riguron.system.internalization.SendMessage;
import me.riguron.system.party.PartyService;
import me.riguron.system.task.TaskFactory;

import java.util.function.Function;

@RequiredArgsConstructor
public class PartySubCommandFactory {

    private final PartyService partyService;
    private final TaskFactory taskFactory;
    private final SendMessage sendMessage;

    public CommandRegistration createCommand(Function<PartyService, PartySubCommand> producer, CommandOptions options) {
        return new CommandRegistration(new AsyncPartySubCommand(taskFactory, producer.apply(partyService), sendMessage), options);
    }

}

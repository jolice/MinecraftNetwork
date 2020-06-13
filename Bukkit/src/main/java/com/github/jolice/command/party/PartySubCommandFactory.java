package com.github.jolice.command.party;

import lombok.RequiredArgsConstructor;
import io.riguron.command.base.CommandOptions;
import io.riguron.command.repository.CommandRegistration;
import io.riguron.system.internalization.SendMessage;
import io.riguron.system.party.PartyService;
import io.riguron.system.task.TaskFactory;

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

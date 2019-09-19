package io.riguron.bukkit.command.party.create;

import io.riguron.bukkit.command.party.PartySubCommand;
import io.riguron.bukkit.command.party.PartySubCommand;
import io.riguron.command.base.CommandOptions;
import io.riguron.system.internalization.Message;
import io.riguron.system.party.PartyService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class PartyCreateCommand extends PartySubCommand {

    public PartyCreateCommand(PartyService partyService) {
        super(partyService, (ps, commandExecution) -> ps.create(commandExecution.getSender().getName()));
    }

}

package io.riguron.bukkit.command.party.remove;

import io.riguron.bukkit.command.party.PartySubCommand;
import io.riguron.bukkit.command.party.PartySubCommand;
import io.riguron.command.base.CommandOptions;
import io.riguron.system.internalization.Message;
import io.riguron.system.party.PartyService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class PartyRemoveCommand extends PartySubCommand {

    public PartyRemoveCommand(PartyService partyService) {
        super(partyService, (ps, commandExecution) -> ps.remove(commandExecution.getSender().getName(), commandExecution.getArguments().get(0)));
    }

}

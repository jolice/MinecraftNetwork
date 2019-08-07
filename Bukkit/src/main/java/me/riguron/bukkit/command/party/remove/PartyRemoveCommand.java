package me.riguron.bukkit.command.party.remove;

import me.riguron.bukkit.command.party.PartySubCommand;
import me.riguron.command.base.CommandOptions;
import me.riguron.system.internalization.Message;
import me.riguron.system.party.PartyService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class PartyRemoveCommand extends PartySubCommand {

    public PartyRemoveCommand(PartyService partyService) {
        super(partyService, (ps, commandExecution) -> ps.remove(commandExecution.getSender().getName(), commandExecution.getArguments().get(0)));
    }

}

package io.riguron.bukkit.command.party.disband;

import io.riguron.bukkit.command.party.PartySubCommand;
import io.riguron.bukkit.command.party.PartySubCommand;
import io.riguron.system.party.PartyService;

public class PartyDisbandCommand extends PartySubCommand {

    public PartyDisbandCommand(PartyService partyService) {
        super(partyService, (ps, commandExecution) -> ps.disband(commandExecution.getSender().getName()));
    }
}

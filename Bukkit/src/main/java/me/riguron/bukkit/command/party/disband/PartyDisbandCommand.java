package me.riguron.bukkit.command.party.disband;

import me.riguron.bukkit.command.party.PartySubCommand;
import me.riguron.system.party.PartyService;

public class PartyDisbandCommand extends PartySubCommand {

    public PartyDisbandCommand(PartyService partyService) {
        super(partyService, (ps, commandExecution) -> ps.disband(commandExecution.getSender().getName()));
    }
}

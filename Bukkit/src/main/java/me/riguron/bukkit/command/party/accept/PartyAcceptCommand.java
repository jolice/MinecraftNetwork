package me.riguron.bukkit.command.party.accept;

import me.riguron.bukkit.command.party.PartySubCommand;
import me.riguron.system.party.PartyService;

public class PartyAcceptCommand extends PartySubCommand {

    public PartyAcceptCommand(PartyService partyService) {
        super(partyService, (ps, commandExecution) -> ps.accept(commandExecution.getArguments().get(0), commandExecution.getSender().getName()));
    }

}

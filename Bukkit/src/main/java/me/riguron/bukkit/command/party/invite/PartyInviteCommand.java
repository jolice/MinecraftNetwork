package me.riguron.bukkit.command.party.invite;

import me.riguron.bukkit.command.party.PartySubCommand;
import me.riguron.system.party.PartyService;

public class PartyInviteCommand extends PartySubCommand {

    public PartyInviteCommand(PartyService partyService) {
        super(partyService, (ps, commandExecution) -> ps.invite(commandExecution.getSender().getName(), commandExecution.getArguments().get(0)));
    }
}

package com.github.jolice.command.party.invite;

import com.github.jolice.command.party.PartySubCommand;
import io.riguron.system.party.PartyService;

public class PartyInviteCommand extends PartySubCommand {

    public PartyInviteCommand(PartyService partyService) {
        super(partyService, (ps, commandExecution) -> ps.invite(commandExecution.getSender().getName(), commandExecution.getArguments().get(0)));
    }
}

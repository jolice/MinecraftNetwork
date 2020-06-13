package com.github.jolice.command.party.disband;

import com.github.jolice.command.party.PartySubCommand;
import io.riguron.system.party.PartyService;

public class PartyDisbandCommand extends PartySubCommand {

    public PartyDisbandCommand(PartyService partyService) {
        super(partyService, (ps, commandExecution) -> ps.disband(commandExecution.getSender().getName()));
    }
}

package com.github.jolice.command.party.create;

import com.github.jolice.command.party.PartySubCommand;
import io.riguron.system.party.PartyService;

public class PartyCreateCommand extends PartySubCommand {

    public PartyCreateCommand(PartyService partyService) {
        super(partyService, (ps, commandExecution) -> ps.create(commandExecution.getSender().getName()));
    }

}

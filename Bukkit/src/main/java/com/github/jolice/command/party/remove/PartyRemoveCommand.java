package com.github.jolice.command.party.remove;

import com.github.jolice.command.party.PartySubCommand;
import io.riguron.system.party.PartyService;

public class PartyRemoveCommand extends PartySubCommand {

    public PartyRemoveCommand(PartyService partyService) {
        super(partyService, (ps, commandExecution) -> ps.remove(commandExecution.getSender().getName(), commandExecution.getArguments().get(0)));
    }

}

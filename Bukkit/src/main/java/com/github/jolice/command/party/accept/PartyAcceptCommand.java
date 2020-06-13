package com.github.jolice.command.party.accept;

import com.github.jolice.command.party.PartySubCommand;
import io.riguron.system.party.PartyService;

public class PartyAcceptCommand extends PartySubCommand {

    public PartyAcceptCommand(PartyService partyService) {
        super(partyService, (ps, commandExecution) -> ps.accept(commandExecution.getArguments().get(0), commandExecution.getSender().getName()));
    }

}

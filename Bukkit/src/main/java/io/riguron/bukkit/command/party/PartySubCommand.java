package io.riguron.bukkit.command.party;

import lombok.RequiredArgsConstructor;
import io.riguron.command.base.CommandExecution;
import io.riguron.command.base.CommandOptions;
import io.riguron.system.party.PartyService;
import io.riguron.system.party.result.Result;

import java.util.function.BiFunction;

@RequiredArgsConstructor
public abstract class PartySubCommand  {

    private final PartyService partyService;
    private final BiFunction<PartyService, CommandExecution, Result> function;

    public Result execute(CommandExecution execution) {
       return function.apply(this.partyService, execution);
    }

}

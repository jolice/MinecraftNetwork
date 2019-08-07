package me.riguron.bukkit.command.party;

import lombok.RequiredArgsConstructor;
import me.riguron.command.base.CommandExecution;
import me.riguron.command.base.CommandOptions;
import me.riguron.system.party.PartyService;
import me.riguron.system.party.result.Result;

import java.util.function.BiFunction;

@RequiredArgsConstructor
public abstract class PartySubCommand  {

    private final PartyService partyService;
    private final BiFunction<PartyService, CommandExecution, Result> function;

    public Result execute(CommandExecution execution) {
       return function.apply(this.partyService, execution);
    }

}

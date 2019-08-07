package me.riguron.command.nested;

import lombok.RequiredArgsConstructor;
import me.riguron.command.base.Command;
import me.riguron.command.engine.CommandExecutor;
import me.riguron.command.repository.CommandRegistration;
import me.riguron.system.internalization.InternalizationService;

import java.util.Map;

@RequiredArgsConstructor
public class NestedCommandFactory  {

    private final InternalizationService internalizationService;
    private final CommandExecutor commandExecutor;

    public Command newNestedCommand(Map<String, CommandRegistration> nestedCommands) {
        return new NestedCommand(nestedCommands, commandExecutor, internalizationService);
    }
}

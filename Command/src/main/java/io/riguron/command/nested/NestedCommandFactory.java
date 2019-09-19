package io.riguron.command.nested;

import io.riguron.command.repository.CommandRegistration;
import lombok.RequiredArgsConstructor;
import io.riguron.command.base.Command;
import io.riguron.command.engine.CommandExecutor;
import io.riguron.command.repository.CommandRegistration;
import io.riguron.system.internalization.InternalizationService;

import java.util.Map;

@RequiredArgsConstructor
public class NestedCommandFactory  {

    private final InternalizationService internalizationService;
    private final CommandExecutor commandExecutor;

    public Command newNestedCommand(Map<String, CommandRegistration> nestedCommands) {
        return new NestedCommand(nestedCommands, commandExecutor, internalizationService);
    }
}

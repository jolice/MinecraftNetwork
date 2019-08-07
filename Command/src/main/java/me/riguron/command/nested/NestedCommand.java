package me.riguron.command.nested;

import me.riguron.command.base.Command;
import me.riguron.command.base.CommandExecution;
import me.riguron.command.engine.CommandExecutor;
import me.riguron.command.repository.CommandRegistration;
import me.riguron.system.internalization.InternalizationService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Nested command is a kind of the command that has multiple sub-commands,
 * i.e a command that nests other command. It means that each argument of a nested
 * command is in turn an independent command (that can be a nested command as well).
 * So, a nested command may be though of as a tree of commands, where arguments of
 * root command are tree nodes. Example:
 * <p>
 * 'party create' - is a nested command, where 'create' is a sub-command that is treated
 * as a completely independent command and associated with some class implementing Command
 * interface.
 */
public class NestedCommand implements Command {

    private final Map<String, CommandRegistration> nestedCommands = new HashMap<>();
    private final CommandExecutor commandExecutor;

    NestedCommand(Map<String, CommandRegistration> nestedCommands, CommandExecutor commandExecutor, InternalizationService internalizationService) {
        this.commandExecutor = commandExecutor;
        this.nestedCommands.putAll(nestedCommands);
        nestedCommands.forEach((s, commandRegistration) -> commandRegistration.getCommandOptions().getAliases().forEach(alias -> {
            nestedCommands.put(alias, commandRegistration);
        }));
    }

    @Override
    public void execute(CommandExecution execution) {
        if (execution.getArguments().isEmpty() || !nestedCommands.containsKey(execution.getArguments().get(0))) {
            this.sendHelp(execution);
        } else {
            this.executeInternal(execution, execution.getArguments().get(0));
        }
    }

    private void executeInternal(CommandExecution execution, String subCommand) {
        CommandRegistration commandRegistration = nestedCommands.get(subCommand);
        String[] sourceArray = execution.getArguments().toArray();
        commandExecutor.execute(commandRegistration, execution.getSender(), Arrays.copyOfRange(sourceArray, 1, sourceArray.length));
    }

    private void sendHelp(CommandExecution execution) {
        nestedCommands.forEach((s, commandRegistration) -> {
            execution.getSender().sendMessage(String.format("/%s %s - %s", execution.getBody(), commandRegistration.getCommandOptions().getBody(),
                    commandRegistration.getCommandOptions().getDescription()));
        });
    }


}

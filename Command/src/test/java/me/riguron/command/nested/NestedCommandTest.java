package me.riguron.command.nested;

import me.riguron.command.arguments.ArgumentSet;
import me.riguron.command.base.CommandExecution;
import me.riguron.command.base.CommandOptions;
import me.riguron.command.engine.CommandExecutor;
import me.riguron.command.repository.CommandRegistration;
import me.riguron.command.sender.Sender;
import me.riguron.system.internalization.InternalizationService;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class NestedCommandTest {


    private CommandExecutor commandExecutor;
    private NestedCommand nestedCommand;
    private Map<String, CommandRegistration> nestedCommands = new HashMap<>();

    @Before
    public void execute() {

        commandExecutor = mock(CommandExecutor.class);

        nestedCommands.put("subA", registration());
        nestedCommands.put("subB", registration());
        nestedCommands.put("subC", registration());

        nestedCommand = new NestedCommand(
                nestedCommands,
                commandExecutor,
                mock(InternalizationService.class)
        );
    }

    @Test
    public void executeExisting() {
        CommandExecution commandExecution = mock(CommandExecution.class);

        ArgumentSet argumentSet = mock(ArgumentSet.class);
        when(argumentSet.get(eq(0))).thenReturn("subA");
        when(argumentSet.isEmpty()).thenReturn(false);
        when(argumentSet.toArray()).thenReturn(new String[]{"subA", "arg1", "arg2"});


        when(commandExecution.getArguments()).thenReturn(
                argumentSet
        );


        nestedCommand.execute(commandExecution);

        verify(commandExecutor).execute(
                eq(nestedCommands.get("subA")),
                any(),
                eq(new String[]{"arg1", "arg2"})
        );

    }

    @Test
    public void executeInexistent() {
        CommandExecution commandExecution = mock(CommandExecution.class);

        ArgumentSet argumentSet = mock(ArgumentSet.class);
        when(argumentSet.get(eq(0))).thenReturn("absent");
        when(argumentSet.isEmpty()).thenReturn(false);
        when(argumentSet.toArray()).thenReturn(new String[]{"absent", "arg1", "arg2"});

        when(commandExecution.getArguments()).thenReturn(
                argumentSet
        );
        when(commandExecution.getSender()).thenReturn(mock(Sender.class));

        nestedCommand.execute(commandExecution);

        verify(commandExecutor, times(0)).execute(
                any(),
                any(),
                any()
        );
    }

    private CommandRegistration registration() {
        CommandRegistration commandRegistration = mock(CommandRegistration.class);
        CommandOptions commandOptions = mock(CommandOptions.class);
        when(commandRegistration.getCommandOptions()).thenReturn(commandOptions);
        when(commandOptions.getAliases()).thenReturn(Collections.emptyList());
        return commandRegistration;

    }
}
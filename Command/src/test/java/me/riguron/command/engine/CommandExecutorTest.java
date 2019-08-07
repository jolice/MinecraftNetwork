package me.riguron.command.engine;

import me.riguron.command.arguments.ArgumentSet;
import me.riguron.command.base.Command;
import me.riguron.command.base.CommandOptions;
import me.riguron.command.repository.CommandRegistration;
import me.riguron.command.sender.Sender;
import me.riguron.system.internalization.SendMessage;
import org.junit.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

public class CommandExecutorTest {

    @Test
    public void execute() {

        CommandOptionsValidator validator = mock(CommandOptionsValidator.class);
        SendMessage sendMessage = mock(SendMessage.class);


        CommandExecutor commandExecutor = new CommandExecutor(
                sendMessage, validator
        );

        when(validator.validate(any(), any(), any())).thenReturn(Optional.empty());

        Command command= mock(Command.class);
        CommandOptions commandOptions = mock(CommandOptions.class);

        CommandRegistration commandRegistration = new CommandRegistration(
                command, commandOptions
        );


        commandExecutor.execute(commandRegistration, mock(Sender.class), new String[]{"body", "arg1", "arg2"});

        verify(command).execute(argThat(execution -> {
            ArgumentSet arguments = execution.getArguments();
            return execution.getBody().equals("body") &&
                    arguments.getAll().equals(Arrays.asList("arg1", "arg2"));
        }));

    }

}
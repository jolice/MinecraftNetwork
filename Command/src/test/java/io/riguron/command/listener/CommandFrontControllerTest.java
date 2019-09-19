package io.riguron.command.listener;

import io.riguron.command.base.CommandOptions;
import io.riguron.command.sender.Sender;
import io.riguron.command.sender.SenderFactory;
import io.riguron.command.base.Command;
import io.riguron.command.base.CommandOptions;
import io.riguron.command.base.SenderKind;
import io.riguron.command.engine.CommandExecutor;
import io.riguron.command.engine.CommandFrontController;
import io.riguron.command.repository.CommandRegistration;
import io.riguron.command.repository.CommandRepository;
import io.riguron.command.sender.Sender;
import io.riguron.command.sender.SenderFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class CommandFrontControllerTest {

    private CommandExecutor executor;
    private CommandFrontController commandFrontController;

    @Before
    public void onIncomingCommand() {

        CommandRepository repository = mock(CommandRepository.class);

        Command command = mock(Command.class);
        CommandOptions options = mock(CommandOptions.class);
        CommandRegistration commandRegistration = new CommandRegistration(
                command,
                options
        );

        SenderFactory senderFactory = mock(SenderFactory.class);
        when(senderFactory.newSender(any(), any())).thenReturn(mock(Sender.class));
        executor = mock(CommandExecutor.class);
        when(repository.getCommand(eq("command"))).thenReturn(Optional.of(commandRegistration));

        commandFrontController = new CommandFrontController(
                repository, executor, senderFactory
        );

    }

    @Test
    public void executeWhenExists() {


        commandFrontController.onIncomingCommand("command arg1 arg2", UUID.randomUUID(), SenderKind.CONSOLE);
        // Command executed
        verify(executor).execute(any(), any(), any());
    }

    @Test
    public void executeWithEmptyBody() {


        commandFrontController.onIncomingCommand("", UUID.randomUUID(), SenderKind.CONSOLE);
        // Command executed
        verify(executor, times(0)).execute(any(), any(), any());
    }

    @Test
    public void whenExecuteInexistentCommand() {
        commandFrontController.onIncomingCommand("cmd", UUID.randomUUID(), SenderKind.CONSOLE);
        verify(executor, times(0)).execute(any(), any(), any());
    }
}
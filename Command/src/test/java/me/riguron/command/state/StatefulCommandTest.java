package me.riguron.command.state;

import me.riguron.command.arguments.ArgumentSet;
import me.riguron.command.base.CommandExecution;
import me.riguron.command.sender.Sender;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class StatefulCommandTest {

    private static final UUID uuid = UUID.randomUUID();

    private CommandStep first, second, third;

    @Before
    public void setUp() {
        third = mock(CommandStep.class);
        second = mock(CommandStep.class);
        first = mock(CommandStep.class);
        when(first.nextStep()).thenReturn(Optional.of(second));
        when(second.nextStep()).thenReturn(Optional.of(third));
        when(third.nextStep()).thenReturn(Optional.empty());

        when(first.execute(any())).thenReturn(true);
        when(second.execute(any())).thenReturn(true);
        when(first.execute(any())).thenReturn(true);
    }

    private CommandExecution commandExecution(boolean emptyArguments) {
        CommandExecution commandExecution = mock(CommandExecution.class);
        when(commandExecution.getSender()).thenAnswer(invocation -> {
            Sender sender = mock(Sender.class);
            when(sender.getId()).thenReturn(uuid);
            return sender;
        });
        when(commandExecution.getArguments()).thenAnswer(invocation -> {
            ArgumentSet argumentSet = mock(ArgumentSet.class);
            when(argumentSet.isEmpty()).thenReturn(emptyArguments);
            return argumentSet;
        });
        return commandExecution;
    }

    @Test
    public void whenAllExecuted() {

        StatefulCommand statefulCommand = new StatefulCommand(first);

        CommandExecution commandExecution = commandExecution(false);

        statefulCommand.execute(commandExecution);
        verify(first).prompt(eq(commandExecution));

        statefulCommand.execute(commandExecution);
        verify(first).execute(eq(commandExecution));
        verify(second).prompt(eq(commandExecution));

        statefulCommand.execute(commandExecution);
        verify(second).execute(eq(commandExecution));
        verify(third).prompt(eq(commandExecution));

        statefulCommand.execute(commandExecution);
        verify(third).execute(eq(commandExecution));
    }

    @Test
    public void whenReset() {

        StatefulCommand statefulCommand = new StatefulCommand(first);

        CommandExecution commandExecution = commandExecution(false);

        statefulCommand.execute(commandExecution);
        verify(first).prompt(eq(commandExecution));

        statefulCommand.execute(commandExecution);
        verify(first).execute(eq(commandExecution));
        verify(second).prompt(eq(commandExecution));

        CommandExecution reset = commandExecution(true);

        statefulCommand.execute(reset);
        verify(first).prompt(eq(reset));

    }

    @Test
    public void whenStepNotPassed() {
        when(first.execute(any())).thenReturn(false);
        reset(first);
        StatefulCommand statefulCommand = new StatefulCommand(first);

        CommandExecution commandExecution = commandExecution(false);
        statefulCommand.execute(commandExecution);

        verify(first).prompt(eq(commandExecution));

        reset(first);
        statefulCommand.execute(commandExecution);
        verify(first).execute(eq(commandExecution));
        verify(first).prompt(eq(commandExecution));
        verify(second, times(0)).execute(any());
   }

}
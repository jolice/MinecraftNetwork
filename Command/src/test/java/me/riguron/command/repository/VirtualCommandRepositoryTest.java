package me.riguron.command.repository;

import me.riguron.command.base.Command;
import me.riguron.command.base.CommandOptions;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class VirtualCommandRepositoryTest {

    private CommandRepository repository;

    @Test
    public void getCommand() {
        repository = new VirtualCommandRepository();

        Command command = mock(Command.class);
        CommandOptions options = mock(CommandOptions.class);
        CommandRegistration commandRegistration = new CommandRegistration(
                command,
                options
        );

        when(options.getAliases()).thenReturn(Arrays.asList("a", "b", "c"));
        when(options.getBody()).thenReturn("body");

        repository.addCommand(commandRegistration);

        assertExists("a");
        assertExists("b");
        assertExists("c");
        assertExists("body");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenOverwriteThenExceptionFired() {
        repository = new VirtualCommandRepository();
        CommandOptions options = mock(CommandOptions.class);
        CommandRegistration commandRegistration = new CommandRegistration(
                mock(Command.class),
                options
        );

        when(options.getBody()).thenReturn("body");

        repository.addCommand(commandRegistration);
        repository.addCommand(commandRegistration);
    }

    private void assertExists(String arg) {
        assertTrue(repository.getCommand(arg).isPresent());
    }

}
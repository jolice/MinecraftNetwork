package io.riguron.command.engine;

import io.riguron.command.base.CommandOptions;
import io.riguron.command.base.PlayerOnlyException;
import io.riguron.command.base.SenderKind;
import io.riguron.command.sender.Sender;
import io.riguron.system.internalization.Message;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CommandOptionsValidatorTest {

    @Test
    public void whenInvalidArgumentLength() {
        CommandOptionsValidator commandOptionsValidator = new CommandOptionsValidator();

        assertTrue(
                commandOptionsValidator.validate(commandOptions(), new String[]{"a"}, mock(Sender.class)).isPresent());
        assertTrue(
                commandOptionsValidator.validate(commandOptions(), new String[]{"a", "b", "c", "d", "e"}, mock(Sender.class)).isPresent());

        Sender sender = mock(Sender.class);
        when(sender.getKind()).thenReturn(SenderKind.PLAYER);
        when(sender.hasPermission(any())).thenReturn(true);
        assertFalse(
                commandOptionsValidator.validate(commandOptions(), new String[]{"a", "b", "c"}, sender).isPresent()
        );
    }

    @Test(expected = PlayerOnlyException.class)
    public void whenConsoleSendsPlayer() {

        CommandOptionsValidator commandOptionsValidator = new CommandOptionsValidator();
        Sender sender = mock(Sender.class);
        when(sender.getKind()).thenReturn(SenderKind.CONSOLE);
        when(sender.hasPermission(any())).thenReturn(true);
        commandOptionsValidator.validate(commandOptions(), new String[]{"a", "b", "c"}, sender);
    }

    @Test
    public void whenDoesNotHavePermission() {
        CommandOptionsValidator optionsValidator = new CommandOptionsValidator();
        Sender sender = mock(Sender.class);
        assertTrue(optionsValidator.validate(commandOptions(), new String[]{"a", "b", "c"}, sender).isPresent());
    }

    private CommandOptions commandOptions() {
        return new CommandOptions() {
            @Override
            public String getBody() {
                return "test";
            }

            @Override
            public Optional<String> getPermission() {
                return Optional.of("permission");
            }

            @Override
            public Message getDescription() {
                return new Message("desc");
            }

            @Override
            public List<String> getAliases() {
                return Collections.emptyList();
            }

            @Override
            public Message getUsage() {
                return new Message("usage");
            }

            @Override
            public int getMinimumNumberOfArguments() {
                return 2;
            }

            @Override
            public int getMaximumNumberOfArguments() {
                return 5;
            }
        };
    }
}
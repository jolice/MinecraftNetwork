package me.riguron.command.repository;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import me.riguron.command.base.Command;
import me.riguron.command.base.CommandOptions;

/**
 * Simple value class that couples a command with its options. Used
 * in command execution engine internals.
 */
@Data
@Setter(AccessLevel.NONE)
public class CommandRegistration {

    private final Command command;
    private final CommandOptions commandOptions;
}

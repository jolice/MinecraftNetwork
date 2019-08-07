package me.riguron.command.base;

/**
 * Exception thrown by command execution engine when a console
 * attempts to execute player-only command.
 */
public class PlayerOnlyException extends RuntimeException {

    public PlayerOnlyException() {
        super("This command is for players only");
    }
}

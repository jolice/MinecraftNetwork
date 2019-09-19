package io.riguron.command.base;

import io.riguron.command.sender.Sender;
import io.riguron.command.arguments.ArgumentSet;
import io.riguron.command.sender.Sender;

/**
 * Represents the context of the command execution.
 */
public interface CommandExecution {

    /**
     * An initiator of the command execution.
     *
     * @return caller of the command
     * @see Sender
     */
    Sender getSender();

    /**
     * List of argument supplied by user on command execution.
     *
     * @return command arguments
     */
    ArgumentSet getArguments();

    /**
     * The body of command that triggered a command execution.
     *
     * @return a string sent by user to server that caused an
     * execution of the command.
     * @see CommandOptions
     */
    String getBody();


}

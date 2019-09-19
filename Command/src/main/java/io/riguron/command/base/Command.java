package io.riguron.command.base;

/**
 * Commands are a primary way of user interaction with server
 * features. Command is a message starting with slash (/) that is
 * associated with a specific server feature.
 */
public interface Command {

    /**
     * Logic performed when user dispatches a certain
     * command.
     *
     * @param execution parameters of current command execution.
     */
    void execute(CommandExecution execution);
}

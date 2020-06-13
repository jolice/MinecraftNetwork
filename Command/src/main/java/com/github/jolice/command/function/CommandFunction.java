package io.riguron.command.function;

import io.riguron.command.base.CommandExecution;
import io.riguron.command.base.CommandExecution;
import io.riguron.system.internalization.Message;

/**
 * Represents an "echo" command, i.e the command
 * that always returns some text response to the initiator.
 */
public interface CommandFunction {

    /**
     * A message returned to the command initiator after an execution.
     *
     * @param execution command execution context
     * @return result of command invocation
     */
    Message execute(CommandExecution execution);

}

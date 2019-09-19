package io.riguron.command.state;

import io.riguron.command.base.CommandExecution;
import io.riguron.command.base.CommandExecution;

import java.util.Optional;

/**
 * Represents a certain step of multi-step (stateful) command.
 * Each step may be treated as an independent command.
 */
public interface CommandStep {

    /**
     * Logic that is performed on the current command step.
     *
     * @param execution command execution context.
     * @return whether the step has been passed and user can proceed to the next one.
     */
    boolean execute(CommandExecution execution);

    /**
     * Any logic that is executed before the user is proceeded
     * to the current step. It can be, for example, a prompt message
     * that instructs the user what should he do at the current step.
     *
     * @param commandExecution command execution context.
     */
    void prompt(CommandExecution commandExecution);

    Optional<CommandStep> nextStep();

}

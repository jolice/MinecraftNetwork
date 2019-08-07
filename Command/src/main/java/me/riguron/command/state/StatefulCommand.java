package me.riguron.command.state;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import me.riguron.command.base.Command;
import me.riguron.command.base.CommandExecution;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * A command that consists of multiple logical steps. An example
 * is a registration command - first user enters login, then password,
 * then email and only then completes a command execution. It looks like:
 * <p>
 * > Enter login:
 * /register login
 * > Enter password:
 * /register password
 * > Enter email:
 * /register mail@google.ru
 * <p>
 * > Done.
 */
public class StatefulCommand implements Command {

    private Cache<UUID, CommandStep> userStates;
    private CommandStep initialState;

    public StatefulCommand(CommandStep initialState) {
        this.initialState = initialState;
        this.userStates = CacheBuilder.newBuilder()
                .maximumSize(10000)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build();
    }

    @Override
    public void execute(CommandExecution commandExecution) {
        final UUID id = commandExecution.getSender().getId();
        CommandStep currentState = userStates.getIfPresent(id);
        Optional<CommandStep> nextState = this.computeNextState(commandExecution, currentState);
        if (nextState.isPresent()) {
            this.userStates.put(id, nextState.get());
        } else {
            this.userStates.invalidate(id);
        }
    }

    private Optional<CommandStep> computeNextState(CommandExecution commandExecution, CommandStep commandStep) {
        CommandStep resultingState;
        if (commandExecution.getArguments().isEmpty() || commandStep == null) {
            resultingState = initialState;
        } else {
            if (commandStep.execute(commandExecution)) {
                Optional<CommandStep> nextState = commandStep.nextStep();
                if (!nextState.isPresent()) {
                    return Optional.empty();
                } else {
                    resultingState = nextState.get();
                }
            } else {
                resultingState = commandStep;
            }
        }
        resultingState.prompt(commandExecution);
        return Optional.of(resultingState);
    }


}

package io.riguron.command.function;

import io.riguron.command.base.Command;
import io.riguron.command.base.CommandExecution;
import lombok.RequiredArgsConstructor;
import io.riguron.command.base.Command;
import io.riguron.command.base.CommandExecution;
import io.riguron.system.internalization.SendMessage;

@RequiredArgsConstructor
public class RespondingCommand implements Command {

    private final SendMessage sendMessage;
    private final CommandFunction commandFunction;

    @Override
    public void execute(CommandExecution execution) {
        sendMessage.to(execution.getSender().getId(), commandFunction.execute(execution));
    }
}

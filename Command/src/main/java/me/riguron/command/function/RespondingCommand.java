package me.riguron.command.function;

import lombok.RequiredArgsConstructor;
import me.riguron.command.base.Command;
import me.riguron.command.base.CommandExecution;
import me.riguron.system.internalization.SendMessage;

@RequiredArgsConstructor
public class RespondingCommand implements Command {

    private final SendMessage sendMessage;
    private final CommandFunction commandFunction;

    @Override
    public void execute(CommandExecution execution) {
        sendMessage.to(execution.getSender().getId(), commandFunction.execute(execution));
    }
}

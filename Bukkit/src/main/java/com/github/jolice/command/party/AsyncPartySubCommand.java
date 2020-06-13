package com.github.jolice.command.party;

import lombok.RequiredArgsConstructor;
import io.riguron.command.base.Command;
import io.riguron.command.base.CommandExecution;
import io.riguron.system.internalization.Message;
import io.riguron.system.internalization.SendMessage;
import io.riguron.system.task.TaskFactory;

@RequiredArgsConstructor
public class AsyncPartySubCommand implements Command {

    private final TaskFactory taskFactory;
    private final PartySubCommand partySubCommand;
    private final SendMessage sendMessage;

    @Override
    public void execute(CommandExecution execution) {
        taskFactory
                .newAsyncFunction(() -> partySubCommand.execute(execution))
                .completeSynchronously(result -> sendMessage.to(execution.getSender().getId(), new Message(result.path() + "." + result.getDescription())));
    }
}

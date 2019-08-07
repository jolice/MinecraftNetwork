package me.riguron.bukkit.command.party;

import lombok.RequiredArgsConstructor;
import me.riguron.command.base.Command;
import me.riguron.command.base.CommandExecution;
import me.riguron.system.internalization.Message;
import me.riguron.system.internalization.SendMessage;
import me.riguron.system.task.TaskFactory;

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

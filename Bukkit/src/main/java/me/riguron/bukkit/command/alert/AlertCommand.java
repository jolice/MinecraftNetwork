package me.riguron.bukkit.command.alert;

import lombok.RequiredArgsConstructor;
import me.riguron.command.base.Command;
import me.riguron.command.base.CommandExecution;
import me.riguron.messaging.MessagingChannels;
import me.riguron.messaging.MessagingService;
import me.riguron.messaging.message.Alert;
import me.riguron.system.task.TaskFactory;

@RequiredArgsConstructor
public class AlertCommand implements Command {

    private final MessagingService messagingService;
    private final TaskFactory taskFactory;

    @Override
    public void execute(CommandExecution execution) {
        taskFactory.newAsyncTask(() -> messagingService.send(new Alert(execution.getArguments().join()), MessagingChannels.PROXY)).execute();
    }
}

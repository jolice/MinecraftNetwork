package com.github.jolice.command.alert;

import lombok.RequiredArgsConstructor;
import io.riguron.command.base.Command;
import io.riguron.command.base.CommandExecution;
import io.riguron.messaging.MessagingChannels;
import io.riguron.messaging.MessagingService;
import io.riguron.messaging.message.Alert;
import io.riguron.system.task.TaskFactory;

@RequiredArgsConstructor
public class AlertCommand implements Command {

    private final MessagingService messagingService;
    private final TaskFactory taskFactory;

    @Override
    public void execute(CommandExecution execution) {
        taskFactory.newAsyncTask(() -> messagingService.send(new Alert(execution.getArguments().join()), MessagingChannels.PROXY)).execute();
    }
}

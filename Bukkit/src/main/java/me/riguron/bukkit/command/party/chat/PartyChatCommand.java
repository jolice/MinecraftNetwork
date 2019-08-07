package me.riguron.bukkit.command.party.chat;

import lombok.RequiredArgsConstructor;
import me.riguron.command.base.Command;
import me.riguron.command.base.CommandExecution;
import me.riguron.command.base.CommandOptions;
import me.riguron.system.internalization.Message;
import me.riguron.system.internalization.SendMessage;
import me.riguron.system.party.PartyService;
import me.riguron.system.task.TaskFactory;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class PartyChatCommand implements Command {

    private final PartyService partyService;
    private final SendMessage sendMessage;
    private final TaskFactory taskFactory;

    @Override
    public void execute(CommandExecution execution) {
        taskFactory
                .newAsyncFunction(() -> partyService.partyChat(execution.getSender().getName(), execution.getArguments().join()))
                .completeSynchronously(res -> {
                    if (res) {
                        sendMessage.to(execution.getSender().getId(), new Message("party.not_in_party"));
                    }
                });
    }

}

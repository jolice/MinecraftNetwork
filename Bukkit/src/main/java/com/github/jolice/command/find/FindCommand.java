package com.github.jolice.command.find;

import lombok.RequiredArgsConstructor;
import io.riguron.command.base.Command;
import io.riguron.command.base.CommandExecution;
import io.riguron.server.repository.PlayerRepository;
import io.riguron.system.internalization.Message;
import io.riguron.system.task.TaskFactory;

@RequiredArgsConstructor
public class FindCommand implements Command {

    private final PlayerRepository playerRepository;
    private final TaskFactory taskFactory;

    @Override
    public void execute(CommandExecution execution) {
        taskFactory
                .newAsyncFunction(() -> playerRepository.getPlayerServer(execution.getArguments().get(0)))
                .completeSynchronously(s -> execution.getSender().sendMessage(s.map(x -> new Message("command.find.result", x))
                .orElseGet(() -> new Message("command.find.offline"))));
    }
}

package me.riguron.bukkit.command.find;

import lombok.RequiredArgsConstructor;
import me.riguron.command.base.Command;
import me.riguron.command.base.CommandExecution;
import me.riguron.server.repository.PlayerRepository;
import me.riguron.system.internalization.Message;
import me.riguron.system.task.TaskFactory;

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

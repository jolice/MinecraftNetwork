package me.riguron.game.setup.command;

import lombok.RequiredArgsConstructor;
import me.riguron.command.base.Command;
import me.riguron.command.base.CommandExecution;
import me.riguron.config.client.ConfigurationSaver;
import me.riguron.game.setup.MapSetup;
import me.riguron.system.internalization.Message;

@RequiredArgsConstructor
public class SaveCommand implements Command {

    private final MapSetup mapSetup;
    private final ConfigurationSaver configurationSaver;

    @Override
    public void execute(CommandExecution execution) {
        configurationSaver.save(mapSetup.getConfig(),
                execution.getArguments().get(0)
        );
        execution.getSender().sendMessage(new Message("Saving configuration"));
    }
}

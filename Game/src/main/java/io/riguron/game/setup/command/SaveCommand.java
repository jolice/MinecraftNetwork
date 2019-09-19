package io.riguron.game.setup.command;

import lombok.RequiredArgsConstructor;
import io.riguron.command.base.Command;
import io.riguron.command.base.CommandExecution;
import io.riguron.config.client.ConfigurationSaver;
import io.riguron.game.setup.MapSetup;
import io.riguron.system.internalization.Message;

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

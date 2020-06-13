package com.github.jolice.game.setup.command;

import com.github.jolice.game.setup.MapSetup;
import lombok.RequiredArgsConstructor;
import com.github.jolice.command.base.Command;
import com.github.jolice.command.base.CommandExecution;
import com.github.jolice.config.client.ConfigurationSaver;
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

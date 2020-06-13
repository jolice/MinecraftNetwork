package com.github.jolice.game.setup.command;

import com.github.jolice.game.setup.SoloMapSetup;
import lombok.RequiredArgsConstructor;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import com.github.jolice.command.base.Command;
import com.github.jolice.command.base.CommandExecution;

@RequiredArgsConstructor
public class SoloAddSpawnCommand implements Command {

    private final SoloMapSetup soloMapSetup;
    private final Server server;

    @Override
    public void execute(CommandExecution execution) {
        Player player = server.getPlayer(execution.getSender().getId());
        player.sendMessage("Added location");
        soloMapSetup.addLocation(player.getLocation());
    }
}

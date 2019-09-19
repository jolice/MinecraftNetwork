package io.riguron.game.setup.command;

import lombok.RequiredArgsConstructor;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import io.riguron.command.base.Command;
import io.riguron.command.base.CommandExecution;
import io.riguron.game.setup.SoloMapSetup;

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

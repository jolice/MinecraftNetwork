package me.riguron.game.setup.command;

import lombok.RequiredArgsConstructor;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import me.riguron.command.base.Command;
import me.riguron.command.base.CommandExecution;
import me.riguron.game.setup.SoloMapSetup;

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

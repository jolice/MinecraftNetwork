package io.riguron.game.setup.command;

import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import io.riguron.command.arguments.ArgumentSet;
import io.riguron.command.base.Command;
import io.riguron.command.base.CommandExecution;

@RequiredArgsConstructor
public class TeleportCommand implements Command {

    private final Server server;

    @Override
    public void execute(CommandExecution execution) {
        Player player = server.getPlayer(execution.getSender().getId());
        try {
            final ArgumentSet arguments = execution.getArguments();
            String world = arguments.get(0);
            Integer x = Integer.valueOf(arguments.get(1));
            Integer y = Integer.valueOf(arguments.get(2));
            Integer z = Integer.valueOf(arguments.get(3));
            Location location = new Location(
                    server.getWorld(world),
                    x,
                    y,
                    z
            );
            player.teleport(location);
        } catch (NumberFormatException e) {
            player.sendMessage("Illegal location format. Expected: [world] [x] [y] [z]");
        }
    }
}

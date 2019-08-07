package me.riguron.game.player;

import lombok.Data;
import org.bukkit.entity.Player;
import me.riguron.game.event.PlayerDropOutEvent;
import me.riguron.game.event.PlayerSpectatorEvent;

@Data
public class BasicPlayerStatus implements GamePlayerStatus {

    private int dropOutTime;
    private boolean alive = true;
    private boolean spectator;
    private final Player player;

    BasicPlayerStatus(Player player) {
        this.player = player;
    }

    @Override
    public void dropOut() {
        this.alive = false;
        this.dropOutTime = (int) (System.currentTimeMillis() / 1000);
        if (player.isOnline()) {
            this.setSpectator(true);
        }
        player.getServer().getPluginManager().callEvent(new PlayerDropOutEvent(player.getUniqueId()));
    }

    @Override
    public void setSpectator(boolean spectator) {
        this.spectator = spectator;
        player.getServer().getPluginManager().callEvent(new PlayerSpectatorEvent(this.player, spectator));
    }
}

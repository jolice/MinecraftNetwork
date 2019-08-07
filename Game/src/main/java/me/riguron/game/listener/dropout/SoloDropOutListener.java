package me.riguron.game.listener.dropout;

import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import me.riguron.game.core.Game;
import me.riguron.game.event.PlayerDropOutEvent;
import me.riguron.game.player.repository.GamePlayerStorage;

@RequiredArgsConstructor
public class SoloDropOutListener implements Listener {

    private final Game game;
    private final GamePlayerStorage<?> gamePlayerStorage;

    @EventHandler
    public void onDropOut(PlayerDropOutEvent event) {
        if (gamePlayerStorage.getAlivePlayerCount() == 1) {
            game.end();
        }
    }
}

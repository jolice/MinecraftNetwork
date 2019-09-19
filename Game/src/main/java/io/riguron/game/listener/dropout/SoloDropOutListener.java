package io.riguron.game.listener.dropout;

import io.riguron.game.core.Game;
import io.riguron.game.player.repository.GamePlayerStorage;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import io.riguron.game.core.Game;
import io.riguron.game.event.PlayerDropOutEvent;
import io.riguron.game.player.repository.GamePlayerStorage;

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

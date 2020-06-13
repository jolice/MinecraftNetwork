package com.github.jolice.game.death;

import com.github.jolice.game.config.GameOptions;
import com.github.jolice.game.core.Game;
import com.github.jolice.game.player.GamePlayer;
import com.github.jolice.game.player.GamePlayerStatus;
import lombok.RequiredArgsConstructor;
import io.riguron.system.task.TaskFactory;

@RequiredArgsConstructor
public class Respawn {

    private final Game game;
    private final TaskFactory taskFactory;
    private final GameOptions gameOptions;

    /**
     * Makes player a spectator for a short period of time,
     * and then returns him back into the game again.
     *
     * @param gamePlayer died player
     */
    public void markForRespawn(GamePlayer gamePlayer) {
        GamePlayerStatus status = gamePlayer.getStatus();
        status.setSpectator(true);
        taskFactory.newSyncDelayedTask(() -> {
            status.setSpectator(false);
            game.getMap().teleportPlayer(gamePlayer.getId());
        }, gameOptions.getRespawnTime()).execute();
    }
}

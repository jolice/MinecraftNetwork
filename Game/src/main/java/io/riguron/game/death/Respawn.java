package io.riguron.game.death;

import io.riguron.game.core.Game;
import io.riguron.game.player.GamePlayer;
import lombok.RequiredArgsConstructor;
import io.riguron.game.config.GameOptions;
import io.riguron.game.core.Game;
import io.riguron.game.player.GamePlayer;
import io.riguron.game.player.GamePlayerStatus;
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

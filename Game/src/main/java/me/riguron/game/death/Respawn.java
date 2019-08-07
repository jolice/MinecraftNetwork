package me.riguron.game.death;

import lombok.RequiredArgsConstructor;
import me.riguron.game.config.GameOptions;
import me.riguron.game.core.Game;
import me.riguron.game.player.GamePlayer;
import me.riguron.game.player.GamePlayerStatus;
import me.riguron.system.task.TaskFactory;

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

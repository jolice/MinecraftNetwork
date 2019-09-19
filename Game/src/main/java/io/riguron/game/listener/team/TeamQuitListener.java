package io.riguron.game.listener.team;

import io.riguron.game.player.repository.GamePlayerStorage;
import io.riguron.game.player.team.TeamPlayer;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerQuitEvent;
import io.riguron.game.listener.state.waiting.WaitingStateListener;
import io.riguron.game.player.repository.GamePlayerStorage;
import io.riguron.game.player.team.TeamPlayer;

@RequiredArgsConstructor
public class TeamQuitListener extends WaitingStateListener {

    private final GamePlayerStorage<TeamPlayer> gamePlayerStorage;

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerQuit(PlayerQuitEvent playerQuitEvent) {
        TeamPlayer player = gamePlayerStorage.getPlayer(playerQuitEvent.getPlayer().getUniqueId());
        player.getTeam().getPlayers().remove(player);
    }


}

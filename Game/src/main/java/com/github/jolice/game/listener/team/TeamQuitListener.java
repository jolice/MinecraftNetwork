package com.github.jolice.game.listener.team;

import com.github.jolice.game.listener.state.waiting.WaitingStateListener;
import com.github.jolice.game.player.repository.GamePlayerStorage;
import com.github.jolice.game.player.team.TeamPlayer;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerQuitEvent;

@RequiredArgsConstructor
public class TeamQuitListener extends WaitingStateListener {

    private final GamePlayerStorage<TeamPlayer> gamePlayerStorage;

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerQuit(PlayerQuitEvent playerQuitEvent) {
        TeamPlayer player = gamePlayerStorage.getPlayer(playerQuitEvent.getPlayer().getUniqueId());
        player.getTeam().getPlayers().remove(player);
    }


}

package com.github.jolice.game.listener.dropout;

import com.github.jolice.game.team.repository.Teams;
import com.github.jolice.game.core.Game;
import com.github.jolice.game.player.repository.GamePlayerStorage;
import com.github.jolice.game.player.team.TeamPlayer;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import com.github.jolice.game.event.PlayerDropOutEvent;

@RequiredArgsConstructor
public class TeamDropOutListener implements Listener {

    private final Game game;
    private final Teams teams;
    private final GamePlayerStorage<TeamPlayer> teamPlayerStorage;

    @EventHandler
    public void onDropOut(PlayerDropOutEvent event) {
        TeamPlayer player = teamPlayerStorage.getPlayer(event.getPlayerId());
        if (!player.getTeam().isAlive()) {
            if (teams.getAliveTeams() == 1) {
                game.end();
            }
        }
    }
}

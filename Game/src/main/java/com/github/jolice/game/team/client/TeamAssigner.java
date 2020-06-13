package com.github.jolice.game.team.client;

import com.github.jolice.game.team.repository.Teams;
import lombok.RequiredArgsConstructor;
import com.github.jolice.game.player.repository.GamePlayerStorage;
import com.github.jolice.game.player.team.TeamPlayer;

@RequiredArgsConstructor
public class TeamAssigner {

    private final GamePlayerStorage<TeamPlayer> gamePlayerStorage;
    private final Teams teams;

    public void assignRemaining() {
        gamePlayerStorage.getAllPlayers().forEach(teamPlayer -> {
            if (!teamPlayer.isInTeam()) {
                teams.getMostFreeTeam().getPlayers().add(teamPlayer);
            }
        });
    }
}

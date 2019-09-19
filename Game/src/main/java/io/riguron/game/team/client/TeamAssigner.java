package io.riguron.game.team.client;

import lombok.RequiredArgsConstructor;
import io.riguron.game.player.repository.GamePlayerStorage;
import io.riguron.game.player.team.TeamPlayer;
import io.riguron.game.team.repository.Teams;

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

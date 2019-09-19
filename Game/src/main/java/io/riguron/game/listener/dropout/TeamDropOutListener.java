package io.riguron.game.listener.dropout;

import io.riguron.game.core.Game;
import io.riguron.game.player.repository.GamePlayerStorage;
import io.riguron.game.player.team.TeamPlayer;
import io.riguron.game.team.repository.Teams;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import io.riguron.game.core.Game;
import io.riguron.game.event.PlayerDropOutEvent;
import io.riguron.game.player.repository.GamePlayerStorage;
import io.riguron.game.player.team.TeamPlayer;
import io.riguron.game.team.repository.Teams;

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

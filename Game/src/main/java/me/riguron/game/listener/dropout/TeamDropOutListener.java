package me.riguron.game.listener.dropout;

import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import me.riguron.game.core.Game;
import me.riguron.game.event.PlayerDropOutEvent;
import me.riguron.game.player.repository.GamePlayerStorage;
import me.riguron.game.player.team.TeamPlayer;
import me.riguron.game.team.repository.Teams;

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

package me.riguron.game.listener.dropout;

import org.junit.Test;
import me.riguron.game.core.Game;
import me.riguron.game.event.PlayerDropOutEvent;
import me.riguron.game.player.repository.GamePlayerStorage;
import me.riguron.game.player.team.TeamPlayer;
import me.riguron.game.team.Team;
import me.riguron.game.team.repository.Teams;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TeamDropOutListenerTest {

    @Test
    public void onDropOut() {

        Game game = mock(Game.class);
        Teams teams = mock(Teams.class);
        GamePlayerStorage<TeamPlayer> storage = mock(GamePlayerStorage.class);
        UUID uuid = UUID.randomUUID();
        PlayerDropOutEvent dropOutEvent = new PlayerDropOutEvent(uuid);

        TeamDropOutListener dropOutListener = new TeamDropOutListener(game, teams, storage);

        TeamPlayer teamPlayer = mock(TeamPlayer.class);
        when(teamPlayer.getTeam()).thenAnswer(invocationOnMock -> {
            Team team = mock(Team.class);
            when(team.isAlive()).thenReturn(false);
            return team;
        });

        when(teams.getAliveTeams()).thenReturn(1);
        when(storage.getPlayer(eq(uuid))).thenReturn(teamPlayer);

        dropOutListener.onDropOut(dropOutEvent);

        verify(game).end();

    }
}
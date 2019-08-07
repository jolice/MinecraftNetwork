package me.riguron.game.listener.state.active;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.junit.Test;
import me.riguron.game.player.spectator.Spectator;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ActiveOnlineIndexerTest {

    @Test
    public void onJoin() {
        Spectator spectator = mock(Spectator.class);
        ActiveJoinListener activeJoinListener = new ActiveJoinListener(spectator);
        Player player = mock(Player.class);
        PlayerJoinEvent event = new PlayerJoinEvent(player, "joined");
        activeJoinListener.onJoin(event);
        verify(spectator).set(eq(player), eq(true));
    }
}
package io.riguron.game.map.type;

import org.bukkit.Location;
import org.junit.Test;
import io.riguron.game.player.GamePlayer;
import io.riguron.game.player.repository.GamePlayerStorage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class SoloGameMapTest {

    private GamePlayerStorage<? super GamePlayer> storage = mock(GamePlayerStorage.class);

    @Test
    public void whenTeleportSinglePlayer() {

        List<Location> locations = locations(10);
        List<? extends GamePlayer> players = players(5);

        UUID uuid = UUID.randomUUID();
        GamePlayer gamePlayer = mock(GamePlayer.class);
        when(gamePlayer.getId()).thenReturn(uuid);
        when(storage.getPlayer(eq(uuid))).thenReturn(gamePlayer);

        createAndTeleport(locations, players, soloGameMap -> soloGameMap.teleportPlayer(uuid));

        verify(gamePlayer).teleport(argThat(locations::contains));
    }

    @Test
    public void whenLessPlayersThanLocationsThenTeleported() {
        List<Location> locations = locations(10);
        List<? extends GamePlayer> players = players(5);

        createAndTeleport(locations, players, SoloGameMap::teleportPlayers);

        for (int i = 0; i < players.size(); i++) {
            verify(players.get(i)).teleport(eq(locations.get(i)));
        }

    }

    @Test(expected = IllegalStateException.class)
    public void whenLessLocationsThenPlayersThenException() {
        createAndTeleport(locations(5), players(10), SoloGameMap::teleportPlayers);
    }

    private void createAndTeleport(List<Location> locations, List<?> players, Consumer<SoloGameMap> action) {


        when(storage.getAllPlayers()).thenAnswer(x -> players);
        SoloGameMap soloGameMap = new SoloGameMap("name", locations, storage);
        action.accept(soloGameMap);
    }

    private List<Location> locations(int size) {
        List<Location> locations = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            locations.add(mock(Location.class));
        }
        return locations;
    }

    private List<? extends GamePlayer> players(int count) {
        if (count == 0)
            return Collections.emptyList();

        List<? super GamePlayer> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            result.add(mock(GamePlayer.class));
        }

        return (List<? extends GamePlayer>) result;
    }


}
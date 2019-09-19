package io.riguron.game.map.type;

import io.riguron.game.player.GamePlayer;
import io.riguron.game.player.repository.GamePlayerStorage;
import io.riguron.game.player.GamePlayer;
import io.riguron.game.player.repository.GamePlayerStorage;
import org.bukkit.Location;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class SoloGameMap extends AbstractGameMap {

    private List<Location> spawnLocations;
    private GamePlayerStorage<?> gamePlayerStorage;

    public SoloGameMap(String name, List<Location> spawnLocations, GamePlayerStorage<?> gamePlayerStorage) {
        super(name);
        this.spawnLocations = spawnLocations;
        this.gamePlayerStorage = gamePlayerStorage;
    }

    @Override
    public void teleportPlayers() {
        Collection<? extends GamePlayer> allPlayers = gamePlayerStorage.getAllPlayers();

        if (allPlayers.size() > spawnLocations.size()) {
            throw new IllegalStateException("Player count exceeds locations counts");
        }

        Iterator<? extends GamePlayer> playerList = allPlayers.iterator();
        Iterator<Location> locationIterator = spawnLocations.iterator();

        while (playerList.hasNext()) {
            playerList.next().teleport(locationIterator.next());
        }
    }

    @Override
    public void teleportPlayer(UUID playerId) {
        gamePlayerStorage
                .getPlayer(playerId)
                .teleport(spawnLocations.get(ThreadLocalRandom.current().nextInt(spawnLocations.size())));
    }

}

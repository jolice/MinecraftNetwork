package io.riguron.game.player.repository;

import lombok.RequiredArgsConstructor;
import io.riguron.game.player.GamePlayer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
public class VirtualGamePlayerStorage<P extends GamePlayer> implements GamePlayerStorage<P> {

    private Map<UUID, P> storage = new HashMap<>();
    private final P absencePlaceholder;

    @Override
    public P getPlayer(UUID player) {
        return storage.getOrDefault(player, absencePlaceholder);
    }

    @Override
    public void addPlayer(UUID player, P gamePlayer) {
        storage.put(player, gamePlayer);
    }

    @Override
    public void removePlayer(UUID player) {
        storage.remove(player);
    }

    @Override
    public Collection<P> getAllPlayers() {
        return storage.values();
    }

    @Override
    public long getAlivePlayerCount() {
        return storage.values().stream().filter(x -> x.getStatus().isAlive()).count();
    }

    @Override
    public boolean contains(UUID uuid) {
        return storage.containsKey(uuid);
    }

}

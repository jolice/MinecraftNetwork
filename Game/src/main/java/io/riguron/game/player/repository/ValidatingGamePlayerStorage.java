package io.riguron.game.player.repository;

import lombok.RequiredArgsConstructor;
import io.riguron.game.player.GamePlayer;

import java.util.Collection;
import java.util.UUID;

@RequiredArgsConstructor
public class ValidatingGamePlayerStorage<P extends GamePlayer> implements GamePlayerStorage<P> {

    private final GamePlayerStorage<P> delegate;

    @Override
    public P getPlayer(UUID playerId) {

        return delegate.getPlayer(playerId);
    }

    @Override
    public void addPlayer(UUID playerId, P gamePlayer) {
        assertIdNotNull(playerId);
        if (gamePlayer == null) {
            throw new IllegalArgumentException("GamePlayer instance can not be null");
        }
        delegate.addPlayer(playerId, gamePlayer);
    }

    @Override
    public void removePlayer(UUID player) {
        assertIdNotNull(player);
        delegate.removePlayer(player);
    }

    @Override
    public Collection<P> getAllPlayers() {
        return delegate.getAllPlayers();
    }

    @Override
    public long getAlivePlayerCount() {
        return delegate.getAlivePlayerCount();
    }

    @Override
    public boolean contains(UUID uuid) {
        assertIdNotNull(uuid);
        return delegate.contains(uuid);
    }

    private void assertIdNotNull(UUID playerId) {
        if (playerId == null) {
            throw new IllegalArgumentException("Player UUID can not be null");
        }
    }
}

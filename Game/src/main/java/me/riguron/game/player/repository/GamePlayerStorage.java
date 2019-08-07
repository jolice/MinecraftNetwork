package me.riguron.game.player.repository;

import me.riguron.game.player.GamePlayer;

import java.util.Collection;
import java.util.UUID;

public interface GamePlayerStorage<P extends GamePlayer> {

    P getPlayer(UUID playerId);

    void addPlayer(UUID player, P gamePlayer);

    void removePlayer(UUID player);

    Collection<P> getAllPlayers();

    long getAlivePlayerCount();

    boolean contains(UUID uuid);

}

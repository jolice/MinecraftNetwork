package me.riguron.server.repository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

/**
 * Repository that stores player-related data: current player's server, proxy and so forth.
 */
public interface PlayerRepository {

    /**
     * Sets current player's server (the server the player is currently playing on).
     *
     * @param server     name of new player's server
     * @param playerName name of the player that has changed the server.
     */
    void setPlayerServer(String server, String playerName);

    /**
     * Returns map containing current proxy of each player from input list.
     *
     * @param playerNames names of the players
     * @return map, where each key is a player name and value is a name of
     * the proxy that he is currently playing on
     */
    Map<String, String> getPlayersProxies(Collection<String> playerNames);


    /**
     * Sets current player's proxy (the proxy the player is currently playing on).
     *
     * @param player     name of the player
     * @param playerName name of the player
     */
    void setPlayerProxy(String player, String playerName);

    /**
     * Returns current player's server.
     *
     * @param playerName name of the player
     * @return optional containing current player's server (or empty, if player is offline).
     */
    Optional<String> getPlayerServer(String playerName);

    /**
     * Returns current player's proxy.
     *
     * @param playerName name of the player
     * @return optional containing current player's proxy (or empty, if player is offline).
     */
    Optional<String> getPlayerProxy(String playerName);

    /**
     * Deletes all player's data from the repository, including current proxy and server.
     *
     * @param player name of the player.
     */
    void deletePlayerData(String player);

}

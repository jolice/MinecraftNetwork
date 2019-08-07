package me.riguron.server.repository;

import me.riguron.server.ServerGroup;

import java.util.Map;

/**
 * Repository that stores count of players currently
 * playing in each server group. Primarily used by servers'
 * selectors to display current online in each of server's groups.
 * Also used to retrieve online players count throughout the server network.
 */
public interface GlobalOnlineRepository {

    /**
     * Fetches current online players' count for each
     * server group.
     *
     * @return mapping of each server group's name to count
     * of online players in that group.
     */
    Map<String, Integer> getGroupsOnline();

    /**
     * Increments online for certain player group.
     *
     * @param group group to be updated
     */
    void increment(ServerGroup group);

    /**
     * Returns count of players in the entire server network.
     *
     * @return how many players are currently playing throughout
     * the server network.
     */
    int getGlobalOnline();

    /**
     * Decrements online for certain player group.
     *
     * @param group group to be updated
     */
    void decrement(ServerGroup group);
}

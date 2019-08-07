package me.riguron.server.repository;

import me.riguron.server.ServerGroup;

import java.util.List;

/**
 * Repository storing online player count of each server in the sorted order,
 * what can be utilized for efficient retrieving of servers with minimal online.
 */
public interface OnlineIndexingRepository {

    /**
     * Returns the server within the specified server group
     * with minimal count of players online.
     *
     * @param group server group to be indexed
     * @return server with minimal count of players online.
     */
    String getMostFreeServer(ServerGroup group);

    /**
     * Returns top servers within the specified server group
     * with minimal count of players online.
     *
     * @param group   server group to be indexed
     * @param options additional options for querying most available servers.
     * @return servers with minimal count of players online.
     */
    List<String> getMostFreeServers(ServerGroup group, FreeServersQueryOptions options);

    /**
     * Removes server from this repository. It's typically done when servers
     * becomes unavailable for joining and therefore should not be indexed in
     * the global list.
     *
     * @param serverGroup server group to be updated
     * @param serverName  name of the server to be removed
     */
    void removeServerData(ServerGroup serverGroup, String serverName);

    /**
     * Increments online player count at specified server within the specified
     * server group.
     *
     * @param serverGroup group of the server
     * @param serverName  name of the server
     */
    void incrementOnline(ServerGroup serverGroup, String serverName);


    /**
     * Decrements online player count at specified server within the specified
     * server group.
     *
     * @param serverGroup group of the server
     * @param serverName  name of the server
     */
    void decrementOnline(ServerGroup serverGroup, String serverName);

    /**
     * Adds server to global indexing repository.
     *
     * @param serverGroup group of the server
     * @param serverName  name of the server
     */
    void addServer(ServerGroup serverGroup, String serverName);
}



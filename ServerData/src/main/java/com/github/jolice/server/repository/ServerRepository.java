package com.github.jolice.server.repository;

import com.github.jolice.server.mapper.ServerMapper;
import com.github.jolice.server.type.Server;

import java.util.List;

/**
 * Repository that stores constantly refreshing servers' data: players online,
 * game status, max players and so forth. Used by system module to pull actual
 * servers' statuses and display them to players.
 * <p>
 * Removing servers is not supported, the responsibility of cleaning up the stale
 * server data lies on the data storage.
 */
public interface ServerRepository {

    /**
     * Returns current server's information.
     *
     * @param name   name of the server
     * @param mapper for deserialization
     * @param <T>    type of the server
     * @return object representing current server's state
     */
    <T extends Server> T get(String name, ServerMapper<T> mapper);

    /**
     * Updates single server's field's value. Example usage: change
     * server's map, status (online / offline) etc.
     *
     * @param serverName name of the server to be updated
     * @param field      name of server's field
     * @param value      new value of the server's field
     * @param <T>        type of the server
     * @see ServerFieldType
     */
    <T> void set(String serverName, ServerFieldType field, T value);

    /**
     * Returns data for multiple servers.
     *
     * @param names  of the servers for which data must be returned
     * @param mapper for deserialization
     * @param <T>    type of the server
     * @return list containing data for servers with specified names.
     */
    <T extends Server> List<T> getAll(List<String> names, ServerMapper<T> mapper);

    /**
     * Increments online player count for specific server.
     *
     * @param serverName name of the server to be updated
     */
    void incrementOnline(String serverName);

    /**
     * Decrements online player count for specific server.
     *
     * @param serverName name of the server to be updated
     */
    void decrementOnline(String serverName);

    /**
     * Adds new server to global repository.
     *
     * @param server object representing new server information
     * @param mapper for serialization
     * @param <T>    type of the server
     */
    <T extends Server> void addServer(T server, ServerMapper<T> mapper);

}

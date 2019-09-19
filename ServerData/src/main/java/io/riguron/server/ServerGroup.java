package io.riguron.server;

/**
 * Represents group of similar servers (lobby servers, specific game servers and so on).
 * Name of each server in the group starts with group name, followed by hyphen and the server
 * number. For example:
 * <p>
 * "bw-11" belongs to server group "bw", or
 * "lobby-1" belongs to server group "lobby".
 */
public interface ServerGroup {

    /**
     * Name of the group. Must be a latin string without any special characters,
     * for example, "bw" or "lobby".
     */
    String getName();
}

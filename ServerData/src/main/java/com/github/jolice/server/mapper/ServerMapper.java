package com.github.jolice.server.mapper;

import com.github.jolice.server.type.Server;

import java.util.Map;

/**
 * Set of functions for serializing and deserializing server objects.
 *
 * @param <T> type of the server
 */
public interface ServerMapper<T extends Server> {

    /**
     * Transforms a string map to the server object.
     *
     * @param data serialized server object
     * @return deserialized server instance.
     */
    T deserialize(Map<String, String> data);

    /**
     * Serializes server object to the map.
     *
     * @param server server to be serialized
     * @return map containing values of properties of the server instance.
     */
    Map<String, String> serialize(T server);


}

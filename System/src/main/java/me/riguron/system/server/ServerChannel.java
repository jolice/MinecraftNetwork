package me.riguron.system.server;

import lombok.RequiredArgsConstructor;
import me.riguron.server.StaticServerGroup;
import me.riguron.server.repository.FreeServersQueryOptions;
import me.riguron.server.repository.OnlineIndexingRepository;
import me.riguron.server.repository.ServerRepository;
import me.riguron.server.type.Server;
import me.riguron.server.mapper.ServerMapper;

import java.util.List;

/**
 * Server channel is actually a synonym for a group of servers of the same kind.
 * Servers in the group have the same prefix, the only difference between them is
 * a number of the server that follows server channel name:
 * <p>
 * lobby-1
 * sw-1
 * <p>
 * here, "lobby" is a channel, a name of the server group, and "1" is a number of
 * the server in the group.
 *
 * @param <T> type of the server
 */
@RequiredArgsConstructor
public class ServerChannel<T extends Server> {

    private final OnlineIndexingRepository onlineIndexingRepository;
    private final String channel;
    private final ServerMapper<T> mapper;
    private final ServerRepository serverRepository;
    private final FreeServersQueryOptions queryOptions;

    /**
     * Fetches list of servers with minimal players online with
     * accordance to the specified query.
     *
     * @return collection of servers with minimum players online.
     */
    public List<T> poll() {
        return serverRepository.getAll(
                onlineIndexingRepository
                        .getMostFreeServers(
                                new StaticServerGroup(channel),
                                queryOptions
                        ),
                mapper);
    }


    /**
     * Most suitable server is a server with lowest online player count
     * in the entire group.
     *
     * @return servers with minimal player count in the whole group.
     */
    public String getMostSuitableServer() {
        return onlineIndexingRepository.getMostFreeServer(new StaticServerGroup(channel));
    }

    public String getGroup() {
        return channel;
    }
}



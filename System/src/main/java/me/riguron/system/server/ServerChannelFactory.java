package me.riguron.system.server;

import lombok.RequiredArgsConstructor;
import me.riguron.server.repository.FreeServersQueryOptions;
import me.riguron.server.repository.OnlineIndexingRepository;
import me.riguron.server.repository.ServerRepository;
import me.riguron.server.type.Server;
import me.riguron.server.mapper.ServerMapper;

@RequiredArgsConstructor
public class ServerChannelFactory {

    private final OnlineIndexingRepository onlineIndexingRepository;
    private final ServerRepository serverRepository;

    public <T extends Server> ServerChannel<T> newServerChannel(String group, ServerMapper<T> mapper, FreeServersQueryOptions queryOptions) {
        return new ServerChannel<>(onlineIndexingRepository, group, mapper, serverRepository, queryOptions);
    }
}

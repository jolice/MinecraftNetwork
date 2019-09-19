package io.riguron.system.server;

import lombok.RequiredArgsConstructor;
import io.riguron.server.repository.FreeServersQueryOptions;
import io.riguron.server.repository.OnlineIndexingRepository;
import io.riguron.server.repository.ServerRepository;
import io.riguron.server.type.Server;
import io.riguron.server.mapper.ServerMapper;

@RequiredArgsConstructor
public class ServerChannelFactory {

    private final OnlineIndexingRepository onlineIndexingRepository;
    private final ServerRepository serverRepository;

    public <T extends Server> ServerChannel<T> newServerChannel(String group, ServerMapper<T> mapper, FreeServersQueryOptions queryOptions) {
        return new ServerChannel<>(onlineIndexingRepository, group, mapper, serverRepository, queryOptions);
    }
}

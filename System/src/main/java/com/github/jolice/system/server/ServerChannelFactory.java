package com.github.jolice.system.server;

import lombok.RequiredArgsConstructor;
import com.github.jolice.server.repository.FreeServersQueryOptions;
import com.github.jolice.server.repository.OnlineIndexingRepository;
import com.github.jolice.server.repository.ServerRepository;
import com.github.jolice.server.type.Server;
import com.github.jolice.server.mapper.ServerMapper;

@RequiredArgsConstructor
public class ServerChannelFactory {

    private final OnlineIndexingRepository onlineIndexingRepository;
    private final ServerRepository serverRepository;

    public <T extends Server> ServerChannel<T> newServerChannel(String group, ServerMapper<T> mapper, FreeServersQueryOptions queryOptions) {
        return new ServerChannel<>(onlineIndexingRepository, group, mapper, serverRepository, queryOptions);
    }
}

package io.riguron.bukkit.server;

import lombok.RequiredArgsConstructor;
import io.riguron.server.ServerName;
import io.riguron.server.repository.ServerFieldType;
import io.riguron.server.repository.ServerRepository;

/**
 * Task responsible for changing server status on "disabled" when server shuts
 * down.
 */
@RequiredArgsConstructor
public class ServerUnregister {

    private final ServerRepository serverRepository;
    private final ServerName serverName;

    public void doUnregister() {
        serverRepository.set(serverName.get(), ServerFieldType.ENABLED, false);
    }
}

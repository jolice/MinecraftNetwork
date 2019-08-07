package me.riguron.bukkit.server;

import lombok.RequiredArgsConstructor;
import me.riguron.server.ServerName;
import me.riguron.server.repository.ServerFieldType;
import me.riguron.server.repository.ServerRepository;

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

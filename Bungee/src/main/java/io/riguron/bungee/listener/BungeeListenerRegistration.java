package io.riguron.bungee.listener;

import lombok.RequiredArgsConstructor;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import io.riguron.system.listener.ListenerRegistration;

@RequiredArgsConstructor
public class BungeeListenerRegistration implements ListenerRegistration<Listener> {

    private final Plugin plugin;

    @Override
    public void registerListener(Listener listener) {
        plugin.getProxy().getPluginManager().registerListener(plugin, listener);
    }
}

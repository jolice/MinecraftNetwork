package io.riguron.bungee.listener;

import io.riguron.bungee.event.PluginDisableEvent;
import lombok.RequiredArgsConstructor;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import io.riguron.common.shutdown.ShutdownHooks;

@RequiredArgsConstructor
public class PluginDisableListener implements Listener {

    private final ShutdownHooks shutdownHooks;

    @EventHandler
    public void onSystemDisable(PluginDisableEvent pluginDisableEvent) {
        shutdownHooks.runAll();
    }

}

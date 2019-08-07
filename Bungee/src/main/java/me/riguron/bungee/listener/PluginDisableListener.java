package me.riguron.bungee.listener;

import lombok.RequiredArgsConstructor;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import me.riguron.bungee.event.PluginDisableEvent;
import me.riguron.common.shutdown.ShutdownHooks;

@RequiredArgsConstructor
public class PluginDisableListener implements Listener {

    private final ShutdownHooks shutdownHooks;

    @EventHandler
    public void onSystemDisable(PluginDisableEvent pluginDisableEvent) {
        shutdownHooks.runAll();
    }

}

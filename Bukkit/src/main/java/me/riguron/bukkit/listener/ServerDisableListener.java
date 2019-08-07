package me.riguron.bukkit.listener;

import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.plugin.Plugin;
import me.riguron.bukkit.server.ServerUnregister;
import me.riguron.common.shutdown.ShutdownHooks;

@RequiredArgsConstructor
public class ServerDisableListener implements Listener {

    private final Plugin plugin;
    private final ShutdownHooks shutdownHooks;
    private final ServerUnregister serverUnregister;

    @EventHandler
    public void onPluginDisable(PluginDisableEvent pluginDisableEvent) {
        if (pluginDisableEvent.getPlugin().equals(this.plugin)) {
            this.kickAll();
            serverUnregister.doUnregister();
            shutdownHooks.runAll();
        }
    }

    private void kickAll() {
        plugin.getServer()
                .getOnlinePlayers()
                .forEach(player -> player.kickPlayer("Server is restarting, please re-join"));

    }
}

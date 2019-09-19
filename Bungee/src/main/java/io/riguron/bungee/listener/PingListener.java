package io.riguron.bungee.listener;

import lombok.RequiredArgsConstructor;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import io.riguron.bungee.data.GlobalOnline;

@RequiredArgsConstructor
public class PingListener implements Listener {

    private final GlobalOnline globalOnline;

    @EventHandler
    public void onProxyPing(ProxyPingEvent event) {
        event.getResponse().getPlayers().setOnline(globalOnline.getGlobalOnline());
    }
}

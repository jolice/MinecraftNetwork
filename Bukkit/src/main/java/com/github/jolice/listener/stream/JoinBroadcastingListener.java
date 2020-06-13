package com.github.jolice.listener.stream;

import com.github.jolice.stream.StreamBroadcast;
import com.github.jolice.stream.StreamType;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

@RequiredArgsConstructor
public class JoinBroadcastingListener implements Listener {

    private final StreamBroadcast streamBroadcast;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        streamBroadcast.onStream(StreamType.JOIN, event.getPlayer().getUniqueId());
    }
}

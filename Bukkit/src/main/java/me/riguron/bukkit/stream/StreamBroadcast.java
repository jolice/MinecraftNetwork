package me.riguron.bukkit.stream;

import lombok.RequiredArgsConstructor;
import me.riguron.system.internalization.SendMessage;

import java.util.UUID;

@RequiredArgsConstructor
public class StreamBroadcast {

    private final StreamMessageProvider streamMessageProvider;
    private final SendMessage sendMessage;

    public void onStream(StreamType streamType, UUID player) {
        sendMessage.to(player, streamMessageProvider.getStreamMessage(streamType, player));
    }
}

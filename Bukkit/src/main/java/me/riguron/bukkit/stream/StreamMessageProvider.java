package me.riguron.bukkit.stream;

import me.riguron.system.internalization.Message;

import java.util.UUID;

/**
 * Function responsible for producing stream (join/leave) messages.
 */
public interface StreamMessageProvider {

    Message getStreamMessage(StreamType streamType, UUID joinedPlayer);
}

package com.github.jolice.system.stream;

import com.github.jolice.system.internalization.Message;


public interface Broadcast {

    /**
     * Sends a message to all online players.
     *
     * @param message raw message
     */
    void broadcast(String message);

    /**
     * Sends an internalized to all online players.
     * Each player will receive a translation of the message
     * depending on his language.
     *
     * @param message internalized message
     */
    void broadcast(Message message);
}

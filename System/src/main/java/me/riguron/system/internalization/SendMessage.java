package me.riguron.system.internalization;

import java.util.UUID;

/**
 * Implementations of this interface are responsible of sending text messages to
 * different targets.
 */
public interface SendMessage {

    /**
     * Sends an internalized message. Target receives a
     * translated message.
     *
     * @param uuid    message receiver
     * @param message message body
     */
    void to(UUID uuid, Message message);

    /**
     * Sends raw text message.
     *
     * @param uuid    message receiver
     * @param message message body
     */
    void to(UUID uuid, String message);
}

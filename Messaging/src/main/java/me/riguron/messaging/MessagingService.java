package me.riguron.messaging;

public interface MessagingService {

    /**
     * Transfers a message to another server through its channel.
     *
     * @param message  object being sent
     * @param receiver server (or group of servers) that receive a message
     */
    void send(Message message, String receiver);

}

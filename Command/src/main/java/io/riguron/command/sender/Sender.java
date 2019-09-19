package io.riguron.command.sender;

import io.riguron.command.base.SenderKind;
import io.riguron.system.internalization.Localizable;
import io.riguron.system.internalization.Message;

import java.util.UUID;

/**
 * Represents an initiator of the command execution (i.e who
 * is able to call commands).
 */
public interface Sender extends Localizable {

    /**
     * ID of command caller. Random ID is generated
     * for a console executor.
     *
     * @return sender's ID
     */
    UUID getId();

    /**
     * Sends an internalized message back to the sender.
     *
     * @param message body of the message
     */
    void sendMessage(Message message);

    /**
     * Sends raw message back to the sender.
     *
     * @param message body of the message
     */
    void sendMessage(String message);

    /**
     * Checks whether a sender has a certain permission.
     * Console is considered to have any permission specified.
     *
     * @param permission target permission
     * @return whether a sender has a certain permission
     */
    boolean hasPermission(String permission);

    /**
     * Name of the sender.
     *
     * @return sender's name
     */
    String getName();

    /**
     * Type of sender (player/console).
     *
     * @return kind of sender
     */
    SenderKind getKind();

}

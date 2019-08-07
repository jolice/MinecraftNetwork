package me.riguron.command.sender;

import me.riguron.command.base.SenderKind;

import java.util.UUID;

public interface SenderFactory {

    Sender newSender(UUID uuid, SenderKind senderKind);
}

package io.riguron.command.sender;

import io.riguron.command.base.SenderKind;

import java.util.UUID;

public interface SenderFactory {

    Sender newSender(UUID uuid, SenderKind senderKind);
}

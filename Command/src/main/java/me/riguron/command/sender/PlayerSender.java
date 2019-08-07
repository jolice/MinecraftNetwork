package me.riguron.command.sender;

import lombok.RequiredArgsConstructor;
import me.riguron.command.base.SenderKind;
import me.riguron.system.internalization.Message;
import me.riguron.system.internalization.SendMessage;
import me.riguron.system.player.PlayerProfile;

import java.util.Locale;
import java.util.UUID;

@RequiredArgsConstructor
public class PlayerSender implements Sender {

    private final PlayerProfile playerProfile;
    private final SendMessage sendMessage;

    @Override
    public UUID getId() {
        return playerProfile.getUuid();
    }

    @Override
    public void sendMessage(Message message) {
        sendMessage.to(playerProfile.getUuid(), message);
    }

    @Override
    public void sendMessage(String message) {
        sendMessage.to(playerProfile.getUuid(), message);
    }

    @Override
    public boolean hasPermission(String permission) {
        return playerProfile.hasPermission(permission);
    }

    @Override
    public String getName() {
        return playerProfile.getName();
    }

    @Override
    public SenderKind getKind() {
        return SenderKind.PLAYER;
    }

    @Override
    public Locale getLocale() {
        return playerProfile.getLocale();
    }
}

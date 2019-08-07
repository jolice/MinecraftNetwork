package me.riguron.command.sender;

import lombok.RequiredArgsConstructor;
import me.riguron.command.base.SenderKind;
import me.riguron.system.internalization.Message;
import me.riguron.system.internalization.SendMessage;

import java.util.Locale;
import java.util.UUID;

@RequiredArgsConstructor
public class ConsoleSender implements Sender {

    /**
     * The UUID for console is fixed. It's useful, for example,
     * for the stateful command that stores stores states using
     * UUID as a key.
     */
    private static final UUID CONSOLE_UUID = UUID.randomUUID();

    private final SendMessage sendMessage;

    @Override
    public UUID getId() {
        return CONSOLE_UUID;
    }

    @Override
    public void sendMessage(Message message) {
        sendMessage.to(getId(), message);
    }

    @Override
    public void sendMessage(String message) {
        sendMessage.to(getId(), message);
    }

    @Override
    public boolean hasPermission(String permission) {
        return true;
    }

    @Override
    public String getName() {
        return "Console";
    }

    @Override
    public SenderKind getKind() {
        return SenderKind.CONSOLE;
    }

    @Override
    public Locale getLocale() {
        return Locale.ENGLISH;
    }
}

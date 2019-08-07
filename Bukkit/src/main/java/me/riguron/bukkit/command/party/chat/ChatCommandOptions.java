package me.riguron.bukkit.command.party.chat;

import me.riguron.command.base.CommandOptions;
import me.riguron.system.internalization.Message;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ChatCommandOptions implements CommandOptions {

    @Override
    public String getBody() {
        return "chat";
    }

    @Override
    public Optional<String> getPermission() {
        return Optional.empty();
    }

    @Override
    public Message getDescription() {
        return new Message("party.command.chat.description");
    }

    @Override
    public List<String> getAliases() {
        return Collections.singletonList("c");
    }

    @Override
    public Message getUsage() {
        return new Message("party.command.chat.usage");
    }

    @Override
    public int getMinimumNumberOfArguments() {
        return 1;
    }

    @Override
    public int getMaximumNumberOfArguments() {
        return 20;
    }
}

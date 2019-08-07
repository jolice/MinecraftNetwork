package me.riguron.bukkit.command.party.invite;

import me.riguron.command.base.CommandOptions;
import me.riguron.system.internalization.Message;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class InviteCommandOptions implements CommandOptions {

    @Override
    public String getBody() {
        return "invite";
    }

    @Override
    public Optional<String> getPermission() {
        return Optional.empty();
    }

    @Override
    public Message getDescription() {
        return new Message( "party.command.invite.description");
    }

    @Override
    public List<String> getAliases() {
        return Collections.singletonList("add");
    }

    @Override
    public Message getUsage() {
        return new Message("party.command.invite.usage");
    }

    @Override
    public int getMinimumNumberOfArguments() {
        return 1;
    }

    @Override
    public int getMaximumNumberOfArguments() {
        return 1;
    }
}

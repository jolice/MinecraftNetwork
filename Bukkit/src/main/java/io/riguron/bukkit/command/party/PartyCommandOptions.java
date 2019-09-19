package io.riguron.bukkit.command.party;

import io.riguron.command.base.CommandOptions;
import io.riguron.system.internalization.Message;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class PartyCommandOptions implements CommandOptions {
    @Override
    public String getBody() {
        return "party";
    }

    @Override
    public Optional<String> getPermission() {
        return Optional.empty();
    }

    @Override
    public Message getDescription() {
        return new Message("party.command.description");
    }

    @Override
    public List<String> getAliases() {
        return Collections.singletonList("p");
    }

    @Override
    public Message getUsage() {
        return new Message("party.command.usage");
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

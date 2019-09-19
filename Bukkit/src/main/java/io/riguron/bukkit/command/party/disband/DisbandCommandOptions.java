package io.riguron.bukkit.command.party.disband;

import io.riguron.command.base.CommandOptions;
import io.riguron.system.internalization.Message;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class DisbandCommandOptions implements CommandOptions {

    @Override
    public String getBody() {
        return "disband";
    }

    @Override
    public Optional<String> getPermission() {
        return Optional.empty();
    }

    @Override
    public Message getDescription() {
        return new Message("party.command.disband.description");
    }

    @Override
    public List<String> getAliases() {
        return Collections.singletonList("destroy");
    }

    @Override
    public Message getUsage() {
        return new Message("party.command.disband.usage");
    }

    @Override
    public int getMinimumNumberOfArguments() {
        return 0;
    }

    @Override
    public int getMaximumNumberOfArguments() {
        return 0;
    }
}

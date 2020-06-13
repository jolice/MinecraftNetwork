package com.github.jolice.command.party.remove;

import io.riguron.command.base.CommandOptions;
import io.riguron.system.internalization.Message;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class RemoveCommandOptions implements CommandOptions {

    @Override
    public String getBody() {
        return "remove";
    }

    @Override
    public Optional<String> getPermission() {
        return Optional.empty();
    }

    @Override
    public Message getDescription() {
        return new Message("party.command.remove.description");
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("del", "rm");
    }

    @Override
    public Message getUsage() {
        return new Message("party.command.remove.usage");
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

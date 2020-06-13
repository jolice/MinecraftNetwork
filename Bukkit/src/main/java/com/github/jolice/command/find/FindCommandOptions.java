package com.github.jolice.command.find;

import io.riguron.command.base.CommandOptions;
import io.riguron.system.internalization.Message;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class FindCommandOptions implements CommandOptions {
    @Override
    public String getBody() {
        return "find";
    }

    @Override
    public Optional<String> getPermission() {
        return Optional.empty();
    }

    @Override
    public Message getDescription() {
        return new Message("command.find.description");
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("where", "f", "lookup");
    }

    @Override
    public Message getUsage() {
        return new Message("command.find.usage");
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

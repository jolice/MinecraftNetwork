package com.github.jolice.command.alert;

import io.riguron.command.base.CommandOptions;
import io.riguron.system.internalization.Message;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class AlertCommandOptions implements CommandOptions {
    @Override
    public String getBody() {
        return "alert";
    }

    @Override
    public Optional<String> getPermission() {
        return Optional.of("system.command.alert");
    }

    @Override
    public Message getDescription() {
        return new Message("command.alert.description");
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("broadcast", "bc");
    }

    @Override
    public Message getUsage() {
        return new Message("command.alert.usage");
    }

    @Override
    public int getMinimumNumberOfArguments() {
        return 1;
    }

    @Override
    public int getMaximumNumberOfArguments() {
        return Integer.MAX_VALUE;
    }
}

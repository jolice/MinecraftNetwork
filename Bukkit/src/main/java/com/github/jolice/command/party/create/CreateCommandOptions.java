package com.github.jolice.command.party.create;

import io.riguron.command.base.CommandOptions;
import io.riguron.system.internalization.Message;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CreateCommandOptions implements CommandOptions {

    @Override
    public String getBody() {
        return "create";
    }

    @Override
    public Optional<String> getPermission() {
        return Optional.empty();
    }

    @Override
    public Message getDescription() {
        return new Message( "party.command.create.description");
    }

    @Override
    public List<String> getAliases() {
        return Collections.singletonList("new");
    }

    @Override
    public Message getUsage() {
        return new Message("party.command.create.usage");
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

package io.riguron.command.repository;

import io.riguron.command.base.CommandOptions;
import io.riguron.command.base.CommandOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Simple in-memory implementation of CommandRepository.
 */
public class VirtualCommandRepository implements CommandRepository {

    private Map<String, CommandRegistration> commands = new HashMap<>();

    @Override
    public Optional<CommandRegistration> getCommand(String body) {
        return Optional.ofNullable(commands.get(body));
    }

    @Override
    public void addCommand(CommandRegistration commandRegistration) {
        CommandOptions commandOptions = commandRegistration.getCommandOptions();
        this.putCommand(commandOptions.getBody(), commandRegistration);
        commandOptions.getAliases().forEach(aliasName -> this.putCommand(aliasName, commandRegistration));
    }

    private void putCommand(String body, CommandRegistration commandRegistration) {
        this.commands.compute(body, (currentKey, currentContainer) -> {
            if (currentContainer != null) {
                throw new IllegalArgumentException(String.format("Attempting to overwrite existing container with key %s", body));
            }
            return commandRegistration;
        });
    }
}

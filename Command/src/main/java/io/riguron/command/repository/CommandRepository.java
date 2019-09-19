package io.riguron.command.repository;

import java.util.Optional;

/**
 * Storage that holds associations of strings with the corresponding command
 * objects.
 */
public interface CommandRepository {

    /**
     * Retrieves a command by its name or alias.
     *
     * @param body string associated with a command
     * @return optional containing appropriate command. Empty optional is returned
     * if there is none.
     */
    Optional<CommandRegistration> getCommand(String body);

    /**
     * Puts command into the storage.
     *
     * @param commandRegistration command and its options
     */
    void addCommand(CommandRegistration commandRegistration);
}

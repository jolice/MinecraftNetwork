package io.riguron.command.base;

import io.riguron.system.internalization.Message;

import java.util.List;
import java.util.Optional;

/**
 * Options of a specific command.
 */
public interface CommandOptions {

    /**
     * Main command body. Body is a root of command,
     * a string that is associated with a certain logic.
     * Body always follows a slash and precedes arguments.
     *
     * @return string that causes an execution of a specific
     * command.
     */
    String getBody();

    /**
     * Permission required for the command to be executed.
     * If command does not require a certain permission to be
     * dispatched, an empty optional object must be returned.
     *
     * @return a permission that must a command sender have to
     * execute the command (or empty optional if none).
     */
    Optional<String> getPermission();

    /**
     * Any information about the command that user should be aware of.
     *
     * @return command description
     */
    Message getDescription();

    /**
     * Additional bodies of the command. Any command can be associated
     * with unlimited count of strings, and this set consists of command
     * aliases and its body.
     *
     * @return additional strings that are associated with a command.
     */
    List<String> getAliases();

    /**
     * Example of command usage. Text of this message is sent to the
     * users to provide an example of how the command can be used.
     * The string of sample usage should not include the command body,
     * it's automatically substituted by the client classes. So the expected
     * format is: "<arg0> <arg1> <arg2>"
     *
     * @return example of how the command can be used
     */
    Message getUsage();


    /**
     * Minimum number of arguments that the command expect.
     *
     * @return the minimum count of arguments that user must supply
     * to the command.
     */
    int getMinimumNumberOfArguments();


    /**
     * Maximum number of arguments that the command expect.
     *
     * @return the maximum count of arguments that user must supply
     * to the command. If there is no upper bound, any large value
     * can be returned.
     */
    int getMaximumNumberOfArguments();

    /**
     * Some commands can be executed only by players, i.e
     * they expect a player entity. The command execution engine
     * will call this method and prevent the command execution if
     * it is initiated by the console, but the command expects a player.
     *
     * @return whether the command can be executed only by player
     */
    default boolean forPlayersOnly() {
        return true;
    }
}

package me.riguron.command.engine;

import me.riguron.command.base.CommandOptions;
import me.riguron.command.base.PlayerOnlyException;
import me.riguron.command.base.SenderKind;
import me.riguron.command.sender.Sender;
import me.riguron.system.internalization.Message;

import java.util.Optional;

public class CommandOptionsValidator {

    /**
     * Checks whether the command preconditions are fulfilled by the command initiator.
     *
     * @param options   options of the command that is executed
     * @param arguments arguments supplied by the user
     * @param sender    command initiator
     * @return optional containing a error message if one of command preconditions is not
     * fulfilled, or empty optional if the command can be executed within the current context.
     * @throws PlayerOnlyException if the player-only command is being dispatched from the console
     */
    public Optional<Message> validate(CommandOptions options, String[] arguments, Sender sender) {
        int argumentsLength = arguments.length - 1;

        if (argumentsLength < options.getMinimumNumberOfArguments() || argumentsLength >= options.getMaximumNumberOfArguments()) {
            return Optional.of(new Message("command.arguments.insufficient"));
        }

        Optional<String> permission = options.getPermission();
        if (permission.isPresent() && !sender.hasPermission(permission.get())) {
            return Optional.of(new Message("permissions.no.permission"));
        }

        if (sender.getKind().equals(SenderKind.CONSOLE) && options.forPlayersOnly()) {
            throw new PlayerOnlyException();
        }

        return Optional.empty();
    }
}

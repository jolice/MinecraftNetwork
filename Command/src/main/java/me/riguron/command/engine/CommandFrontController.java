package me.riguron.command.engine;

import lombok.RequiredArgsConstructor;
import me.riguron.command.base.SenderKind;
import me.riguron.command.repository.CommandRegistration;
import me.riguron.command.repository.CommandRepository;
import me.riguron.command.sender.Sender;
import me.riguron.command.sender.SenderFactory;

import java.util.Optional;
import java.util.UUID;

/**
 * Class that servers as an external interface for coupling the command execution
 * engine and minecraft command listeners.
 */
@RequiredArgsConstructor
public class CommandFrontController {

    private final CommandRepository commandRepository;
    private final CommandExecutor commandExecutor;
    private final SenderFactory senderFactory;

    /**
     * Method that causes a certain command to be executed by delegating
     * data supplied from the minecraft command listeners to the command
     * execution engine.
     *
     * @param message full text of command executed by the sender, including arguments, without slash
     * @param fromId  UUID of the command sender (a random value for the console sender).
     * @param kind    type of command sender
     * @return whether the specified command exists
     */
    public boolean onIncomingCommand(String message, UUID fromId, SenderKind kind) {
        return !message.isEmpty() && executeIfExists(message.split("\\s+"), senderFactory.newSender(fromId, kind));
    }

    private boolean executeIfExists(String[] bodyParts, Sender sender) {
        Optional<CommandRegistration> command = commandRepository.getCommand(bodyParts[0]);
        if (command.isPresent()) {
            CommandRegistration commandRegistration = command.get();
            commandExecutor.execute(commandRegistration, sender, bodyParts);
        }
        return command.isPresent();
    }


}

package me.riguron.command.engine;

import lombok.RequiredArgsConstructor;
import me.riguron.command.arguments.ListArgumentSet;
import me.riguron.command.arguments.ValidatingArgumentSet;
import me.riguron.command.base.Command;
import me.riguron.command.base.StaticCommandExecution;
import me.riguron.command.repository.CommandRegistration;
import me.riguron.command.sender.Sender;
import me.riguron.system.internalization.Message;
import me.riguron.system.internalization.SendMessage;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CommandExecutor {

    private final SendMessage sendMessage;
    private final CommandOptionsValidator validator;

    public void execute(CommandRegistration commandContainer, Sender sender, String[] arguments) {
        Command command = commandContainer.getCommand();

        Optional<Message> validationErrorMessage = validator.validate(commandContainer.getCommandOptions(), arguments, sender);

        if (validationErrorMessage.isPresent()) {
            sendMessage.to(sender.getId(), validationErrorMessage.get());
            sendMessage.to(sender.getId(), commandContainer.getCommandOptions().getUsage());
        } else {
            List<String> argumentCollection = Arrays.asList(arguments).subList(1, arguments.length);
            command.execute(
                    new StaticCommandExecution(sender, new ValidatingArgumentSet(
                            new ListArgumentSet(argumentCollection)),
                            arguments[0]));
        }
    }


}

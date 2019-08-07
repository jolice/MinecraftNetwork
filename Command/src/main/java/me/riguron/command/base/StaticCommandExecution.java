package me.riguron.command.base;

import lombok.Value;
import me.riguron.command.arguments.ArgumentSet;
import me.riguron.command.sender.Sender;

@Value
public class StaticCommandExecution implements CommandExecution {

    private final Sender sender;
    private final ArgumentSet arguments;
    private final String body;
}

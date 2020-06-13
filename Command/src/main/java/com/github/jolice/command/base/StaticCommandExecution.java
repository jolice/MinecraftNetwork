package io.riguron.command.base;

import io.riguron.command.arguments.ArgumentSet;
import io.riguron.command.sender.Sender;
import lombok.Value;
import io.riguron.command.arguments.ArgumentSet;
import io.riguron.command.sender.Sender;

@Value
public class StaticCommandExecution implements CommandExecution {

    private final Sender sender;
    private final ArgumentSet arguments;
    private final String body;
}

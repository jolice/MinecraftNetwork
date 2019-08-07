package me.riguron.command.function;

import me.riguron.command.base.CommandExecution;
import me.riguron.command.sender.Sender;
import me.riguron.system.internalization.Message;
import me.riguron.system.internalization.SendMessage;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.*;

public class RespondingCommandTest {

    @Test
    public void execute() {


        CommandFunction commandFunction = mock(CommandFunction.class);
        SendMessage sendMessage = mock(SendMessage.class);

        Message response = mock(Message.class);

        when(commandFunction.execute(any())).thenReturn(response);

        RespondingCommand respondingCommand = new RespondingCommand(
                sendMessage, commandFunction
        );

        CommandExecution commandExecution = mock(CommandExecution.class);
        when(commandExecution.getSender()).thenReturn(mock(Sender.class));
        respondingCommand.execute(commandExecution);

        verify(sendMessage).to(any(), same(response));

    }
}
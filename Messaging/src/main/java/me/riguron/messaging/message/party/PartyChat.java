package me.riguron.messaging.message.party;

import lombok.*;
import me.riguron.messaging.Message;


@Data
@Setter(AccessLevel.NONE)
@AllArgsConstructor
@NoArgsConstructor
public class PartyChat implements Message {

    private String receiver;
    private String text;
    private String from;
}

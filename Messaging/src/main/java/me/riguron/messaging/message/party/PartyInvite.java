package me.riguron.messaging.message.party;

import lombok.*;
import me.riguron.messaging.Message;

@Data
@Setter(AccessLevel.NONE)
@AllArgsConstructor
@NoArgsConstructor
public class PartyInvite implements Message {

    private String owner;
    private String invited;

}

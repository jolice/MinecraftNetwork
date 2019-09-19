package io.riguron.messaging.message.party;

import lombok.*;
import io.riguron.messaging.Message;

@Data
@Setter(AccessLevel.NONE)
@AllArgsConstructor
@NoArgsConstructor
public class PartyTeleport implements Message {

    private String player;
    private String targetServer;
}

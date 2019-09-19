package io.riguron.messaging.message.party;

import io.riguron.messaging.Message;
import lombok.*;

@Data
@Setter(AccessLevel.NONE)
@AllArgsConstructor
@NoArgsConstructor
public class PartyDisband implements Message {

    private String memberName;
}

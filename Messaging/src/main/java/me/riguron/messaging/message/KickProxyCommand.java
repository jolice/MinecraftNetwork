package me.riguron.messaging.message;

import lombok.*;
import me.riguron.messaging.Message;

@Data
@Setter(AccessLevel.NONE)
@AllArgsConstructor
@NoArgsConstructor
public class KickProxyCommand implements Message {

    private String playerName;
    private String reason;
}

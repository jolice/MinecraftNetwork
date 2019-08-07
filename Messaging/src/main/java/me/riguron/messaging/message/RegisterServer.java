package me.riguron.messaging.message;

import lombok.*;
import me.riguron.messaging.Message;

@Data
@Setter(AccessLevel.NONE)
@AllArgsConstructor
@NoArgsConstructor
public class RegisterServer implements Message {

    private String name;
    private String address;
    private int port;
    private int maxPlayers;

}

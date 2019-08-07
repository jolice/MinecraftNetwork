package me.riguron.messaging.message;

import lombok.*;
import me.riguron.messaging.Message;


@Data
@Setter(AccessLevel.NONE)
@AllArgsConstructor
@NoArgsConstructor
public class PrivateMessageCommand implements Message {

    private String from;
    private String text;

}

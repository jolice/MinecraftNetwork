package io.riguron.messaging.message;

import lombok.*;
import io.riguron.messaging.Message;


@Data
@Setter(AccessLevel.NONE)
@AllArgsConstructor
@NoArgsConstructor
public class PrivateMessageCommand implements Message {

    private String from;
    private String text;

}

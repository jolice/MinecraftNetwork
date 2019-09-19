package io.riguron.messaging.message;

import lombok.*;
import io.riguron.messaging.Message;

@Data
@Setter(AccessLevel.NONE)
@AllArgsConstructor
@NoArgsConstructor
public class Alert implements Message {

    private String message;

}

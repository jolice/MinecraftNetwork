package me.riguron.game.event;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.UUID;

@Value
@EqualsAndHashCode(callSuper = true)
public class PlayerDropOutEvent extends GameEvent {

    private final UUID playerId;
}

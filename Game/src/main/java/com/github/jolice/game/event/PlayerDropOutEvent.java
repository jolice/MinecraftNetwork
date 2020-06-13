package com.github.jolice.game.event;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.UUID;

@Value
@EqualsAndHashCode(callSuper = true)
public class PlayerDropOutEvent extends GameEvent {

    private final UUID playerId;
}

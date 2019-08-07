package me.riguron.system.punishment.param;

import lombok.Value;

import java.time.Duration;

@Value
public class PunishmentParameters {

    /**
     * Player that is punished.
     */
    private String targetName;

    /**
     * A player that applied the punishment (or console).
     */
    private String punisher;

    /**
     * Reason of the punishment (or empty if none).
     */
    private String reason;

    /**
     * Duration of the punishment, if it's not permanent.
     */
    private Duration duration;

}

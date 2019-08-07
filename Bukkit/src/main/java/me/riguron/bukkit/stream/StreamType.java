package me.riguron.bukkit.stream;

import lombok.Getter;

/**
 * Type of the stream.
 */
@Getter
public enum StreamType {

    /**
     * Player joins
     */
    JOIN("join"),

    /**
     * Player quits
     */
    QUIT("quit");

    private String description;

    StreamType(String description) {
        this.description = description;
    }
}

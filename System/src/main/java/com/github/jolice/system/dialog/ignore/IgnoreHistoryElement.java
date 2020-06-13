package com.github.jolice.system.dialog.ignore;

import lombok.Value;

import java.time.LocalDateTime;

/**
 * DTO for the ignore history.
 */
@Value
public class IgnoreHistoryElement {

    /**
     * Player being ignored.
     */
    private final String target;

    /**
     * Date when player started ignoring the target,
     * i.e the date of record creation.
     */
    private final LocalDateTime date;

}

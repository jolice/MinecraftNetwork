package io.riguron.game.team;

import org.bukkit.DyeColor;
import io.riguron.game.player.GameScore;

public interface Team {

    /**
     * Color of the team. Displayed in the chat, scoreboard,
     * tab list, etc.
     *
     * @return team's color.
     */
    DyeColor getColor();

    String getName();

    TeamPlayers getPlayers();

    /**
     * Whether at least one of players in the team is alive.
     *
     * @return whether the team has alive players.
     */
    boolean isAlive();

    default boolean isFull() {
        return getPlayers().count() == getPlayers().max();
    }

    GameScore getScore();

}

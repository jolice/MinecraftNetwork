package me.riguron.game.player;

public interface GamePlayerStatus {

    /**
     * Drops the player out of the game.
     */
    void dropOut();

    /**
     * Marks player as a spectator (or returns player to the game by removing the
     * spectator mode for this player).
     *
     * @param spectator true for making player a spectator, false for returning player to the game
     */
    void setSpectator(boolean spectator);

    boolean isAlive();

    boolean isSpectator();

    /**
     * Time in millis when the player was dropped out.
     *
     * @return time of player's drop out.
     */
    int getDropOutTime();
}

package me.riguron.game.winner;

public interface WinningHandler {

    /**
     * Represents an action that happens when the game ends,
     * including winner calculation, stats saving and so forth.
     */
    void gameFinished();
}

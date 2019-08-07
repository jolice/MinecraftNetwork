package me.riguron.game.core;

public enum GameState {

    /**
     * The game is not running, player recruitment is in progress.
     * Players join waiting lobby, select teams, kits and so on.
     */
    WAITING,

    /**
     * This state is actually a part of waiting state, but it also indicates
     * that the game starts soon, when the waiting countdown ends.
     */
    COUNTDOWN,

    /**
     * Waiting is finished, the game is now active, but players still can not participate
     * in game, until the states switches to active. This state is not used in games that
     * have disabled delay in the options.
     *
     * @see me.riguron.game.config.GameOptions
     */
    STARTING,

    /**
     * Main part of the game. Players participate in a game, game events are listened to.
     */

    ACTIVE,

    /**
     * This state represents game end, but currently does not differ from the active state.
     */
    END;

}

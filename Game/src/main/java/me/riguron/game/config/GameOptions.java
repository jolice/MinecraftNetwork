package me.riguron.game.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.riguron.config.Configuration;
import me.riguron.game.core.GameDeathType;
import me.riguron.game.core.GameKind;
import org.bukkit.Location;

@Getter
@EqualsAndHashCode
@ToString
@Data
public class GameOptions implements Configuration {

    /**
     * Minimal number of players that is required for the game start.
     * When number of players online reaches this value, the game timer
     * starts. If player leaves during the game start countdown and number
     * of players online gets less than this value, the game timer stops.
     */
    private int minimumPlayersToStart = 10;

    /**
     * The time after which the game ends automatically.
     */
    private int expirationTime = 600;

    /**
     * Type of the game (either solo or team).
     */
    private GameKind gameKind = GameKind.SOLO;

    /**
     * A location where the players are teleported on join during the waiting state.
     */
    private Location lobby;

    /**
     * The time after which a player re-spawns.
     */
    private int respawnTime = 3;

    /**
     * Maximal number of players that may participate in the game.
     */
    private int maxPlayers = 15;

    /**
     * Duration of the countdown.
     */
    private int timeToStart = 10;

    /**
     * Whether the game start is delayed (i.e players have to wait some time
     * between the countdown end and the actual game starts).
     */
    private boolean delay = true;

    /**
     * Affects the choice of the death handler.
     *
     * @see me.riguron.game.death.handler.PlayerDeathHandler
     */
    private GameDeathType deathType = GameDeathType.RESPAWN;

    /**
     * Name of the game.
     */
    private String gameName = "Unnamed game";
}

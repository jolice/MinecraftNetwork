package io.riguron.game.config.advanced;

import lombok.*;
import io.riguron.config.Configuration;

@Getter
@EqualsAndHashCode
@ToString
public class AdvancedGameOptions implements Configuration {

    /**
     * Options related to the blocks.
     */
    private BlockOptions blockOptions = new BlockOptions();

    /**
     * Options related to any state changes (weather, food, etc.)
     */
    private ChangingOptions changingOptions = new ChangingOptions();

    /**
     * Options related to the entity or the player damage.
     */
    private DamageOptions damageOptions = new DamageOptions();

    /**
     * Options related to the inventory items, item drops and so forth.
     */
    private ItemOptions itemOptions = new ItemOptions();

    /**
     * Options related to players and their actions.
     */
    private PlayerOptions playerOptions = new PlayerOptions();

}

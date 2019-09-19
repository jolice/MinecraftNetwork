package io.riguron.game.config.team;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.bukkit.DyeColor;

@Getter
@EqualsAndHashCode
@ToString
public class TeamConfig {

    private DyeColor color;
    private String name;
}

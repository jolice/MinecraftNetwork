package com.github.jolice.game.config.team;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import com.github.jolice.game.config.GameOptions;

import java.util.List;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString
public class TeamGameConfig extends GameOptions {

    private List<TeamConfig> teams;
    private int teamSize;
}

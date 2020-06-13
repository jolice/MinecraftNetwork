package com.github.jolice.game.team;

import lombok.RequiredArgsConstructor;
import org.bukkit.DyeColor;
import com.github.jolice.game.player.GameScore;

@RequiredArgsConstructor
public class StandardTeam implements Team {

    private final DyeColor color;
    private final String name;
    private final GameScore gameScore = new GameScore();
    private final TeamPlayers teamPlayers;

    public StandardTeam(DyeColor color, String name, int maxPlayerCount) {
        this.color = color;
        this.name = name;
        this.teamPlayers = new DefaultTeamPlayers(maxPlayerCount, this);
    }

    @Override
    public DyeColor getColor() {
        return color;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public TeamPlayers getPlayers() {
        return teamPlayers;
    }

    @Override
    public boolean isAlive() {
        return teamPlayers.alive() > 0;
    }

    @Override
    public GameScore getScore() {
        return gameScore;
    }

}

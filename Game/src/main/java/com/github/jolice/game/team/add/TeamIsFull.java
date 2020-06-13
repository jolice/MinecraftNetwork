package com.github.jolice.game.team.add;

import com.github.jolice.game.team.Team;

public class TeamIsFull extends AbstractAddResult {

    public TeamIsFull(Team team) {
        super(team);
    }

    @Override
    String messageDescription() {
        return "team.add.is_full";
    }
}

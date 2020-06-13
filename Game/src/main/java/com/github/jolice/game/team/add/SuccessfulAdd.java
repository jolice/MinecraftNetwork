package com.github.jolice.game.team.add;

import com.github.jolice.game.team.Team;

public class SuccessfulAdd extends AbstractAddResult {

    public SuccessfulAdd(Team team) {
        super(team);
    }

    @Override
    String messageDescription() {
        return "teams.add.success";
    }
}

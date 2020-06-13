package com.github.jolice.game.team.add;

import com.github.jolice.game.team.Team;

public class AlreadyInCurrentTeam extends AbstractAddResult {

    public AlreadyInCurrentTeam(Team team) {
        super(team);
    }

    @Override
    String messageDescription() {
        return "teams.add.already_in_team";
    }
}

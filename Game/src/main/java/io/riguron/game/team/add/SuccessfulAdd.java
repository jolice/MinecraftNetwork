package io.riguron.game.team.add;

import io.riguron.game.team.Team;
import io.riguron.game.team.Team;

public class SuccessfulAdd extends AbstractAddResult {

    public SuccessfulAdd(Team team) {
        super(team);
    }

    @Override
    String messageDescription() {
        return "teams.add.success";
    }
}

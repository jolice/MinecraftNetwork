package io.riguron.game.team.add;

import io.riguron.game.team.Team;
import io.riguron.game.team.Team;

public class TeamIsFull extends AbstractAddResult {

    public TeamIsFull(Team team) {
        super(team);
    }

    @Override
    String messageDescription() {
        return "team.add.is_full";
    }
}

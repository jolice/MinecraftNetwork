package me.riguron.game.team.add;

import me.riguron.game.team.Team;
import me.riguron.game.team.Team;

public class TeamIsFull extends AbstractAddResult {

    public TeamIsFull(Team team) {
        super(team);
    }

    @Override
    String messageDescription() {
        return "team.add.is_full";
    }
}

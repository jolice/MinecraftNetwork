package me.riguron.game.team.add;

import me.riguron.game.team.Team;
import me.riguron.game.team.Team;

public class SuccessfulAdd extends AbstractAddResult {

    public SuccessfulAdd(Team team) {
        super(team);
    }

    @Override
    String messageDescription() {
        return "teams.add.success";
    }
}

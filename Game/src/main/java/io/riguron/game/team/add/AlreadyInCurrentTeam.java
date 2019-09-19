package io.riguron.game.team.add;

import io.riguron.game.team.Team;
import io.riguron.game.team.Team;

public class AlreadyInCurrentTeam extends AbstractAddResult {

    public AlreadyInCurrentTeam(Team team) {
        super(team);
    }

    @Override
    String messageDescription() {
        return "teams.add.already_in_team";
    }
}

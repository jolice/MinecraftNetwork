package me.riguron.game.team.add;

import me.riguron.game.team.Team;
import me.riguron.game.team.Team;

public class AlreadyInCurrentTeam extends AbstractAddResult {

    public AlreadyInCurrentTeam(Team team) {
        super(team);
    }

    @Override
    String messageDescription() {
        return "teams.add.already_in_team";
    }
}

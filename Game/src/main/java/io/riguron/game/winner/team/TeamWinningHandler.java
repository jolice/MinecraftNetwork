package io.riguron.game.winner.team;

import io.riguron.game.team.Team;
import io.riguron.game.winner.WinningHandler;
import lombok.RequiredArgsConstructor;
import io.riguron.game.team.Team;
import io.riguron.game.winner.WinningHandler;
import io.riguron.system.internalization.Message;
import io.riguron.system.stream.Broadcast;

@RequiredArgsConstructor
public class TeamWinningHandler implements WinningHandler {

    private final TeamResultCalculation teamResultCalculation;
    private final Broadcast broadcast;

    @Override
    public void gameFinished() {
        Team team = teamResultCalculation.calculateWinningTeam();
        broadcast.broadcast(new Message("game.winning.team", team.getName()));
    }
}

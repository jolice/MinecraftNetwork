package me.riguron.game.winner.team;

import lombok.RequiredArgsConstructor;
import me.riguron.game.team.Team;
import me.riguron.game.winner.WinningHandler;
import me.riguron.system.internalization.Message;
import me.riguron.system.stream.Broadcast;

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

package me.riguron.game.listener.change;

import lombok.RequiredArgsConstructor;
import me.riguron.game.core.Game;
import me.riguron.game.core.GameState;
import me.riguron.game.event.GameStateChangeEvent;
import me.riguron.game.map.vote.GameMapVoting;
import me.riguron.server.ServerName;
import me.riguron.server.repository.ServerFieldType;
import me.riguron.server.repository.ServerRepository;
import me.riguron.system.internalization.Message;
import me.riguron.system.stream.Broadcast;


@RequiredArgsConstructor
public class GameCountdownListener extends SingleStateChangeListener {

    private final GameMapVoting gameMapVoting;
    private final Broadcast broadcast;
    private final ServerRepository serverRepository;

    @Override
    protected void onGameStateChange(GameStateChangeEvent gameStateChangeEvent) {
        this.closeVoting(gameStateChangeEvent.getGame());
    }

    private void closeVoting(Game game) {
        if (gameMapVoting.isOpen()) {
            gameMapVoting.getVotingWinner().ifPresent(gameMap -> {
                game.setMap(gameMap);
                serverRepository.set(ServerName.INSTANCE.get(), ServerFieldType.MAP, gameMap.getName());
                broadcast.broadcast(new Message("game.voting.map.won", gameMap.getName()));
            });
            gameMapVoting.close();
        }
    }

    @Override
    protected GameState getTriggerState() {
        return GameState.COUNTDOWN;
    }
}

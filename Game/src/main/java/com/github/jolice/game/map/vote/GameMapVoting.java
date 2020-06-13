package com.github.jolice.game.map.vote;

import com.github.jolice.game.map.GameMaps;
import com.github.jolice.game.map.vote.result.MapVotingResult;
import com.github.jolice.game.map.vote.result.SuccessfulVoteResult;
import com.github.jolice.game.map.vote.result.UnknownMapResult;
import lombok.RequiredArgsConstructor;
import com.github.jolice.game.map.GameMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class GameMapVoting {

    private final GameMaps gameMaps;

    private Map<GameMap, Integer> votes = new HashMap<>();
    private boolean open;

    public MapVotingResult vote(String mapName) {
        return gameMaps.getMap(mapName)
                .map(this::successfulVoting)
                .orElseGet(() -> new UnknownMapResult(mapName));
    }

    public Optional<GameMap> getVotingWinner() {
        return votes.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .flatMap(gameMapIntegerEntry -> Optional.of(gameMapIntegerEntry.getKey()));
    }

    private MapVotingResult successfulVoting(GameMap gameMap) {
        return new SuccessfulVoteResult(votes.merge(gameMap, 1, Integer::sum), gameMap.getName());
    }

    public void close() {
        this.open = false;
    }

    public boolean isOpen() {
        return this.open;
    }
}

package com.github.jolice.game.winner.solo;

import com.github.jolice.game.winner.solo.order.Place;
import com.github.jolice.game.winner.solo.order.PlaceOrderBy;
import lombok.RequiredArgsConstructor;
import com.github.jolice.game.player.GamePlayer;
import com.github.jolice.game.player.repository.GamePlayerStorage;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
public class OrderedResultCalculation implements SoloResultCalculation {

    private final GamePlayerStorage<?> gamePlayerStorage;
    private final PlaceOrderBy placeOrderBy;

    @Override
    public List<Place> getPlaces() {
        List<GamePlayer> allPlayers = sort();
        return IntStream.range(0, allPlayers.size())
                .mapToObj(value -> new Place(value + 1, allPlayers.get(value)))
                .collect(Collectors.toList());
    }

    private List<GamePlayer> sort() {
        return gamePlayerStorage
                .getAllPlayers()
                .stream()
                .sorted(Comparator.comparing(placeOrderBy).reversed())
                .collect(Collectors.toList());
    }
}

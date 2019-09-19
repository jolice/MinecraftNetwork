package io.riguron.game.winner.solo;

import io.riguron.game.player.GamePlayer;
import io.riguron.game.player.GameScore;
import io.riguron.game.player.repository.GamePlayerStorage;
import io.riguron.game.winner.solo.order.Place;
import io.riguron.game.winner.solo.order.PlaceOrderByScore;
import org.junit.Assert;
import org.junit.Test;
import io.riguron.game.player.GamePlayer;
import io.riguron.game.player.GameScore;
import io.riguron.game.player.repository.GamePlayerStorage;
import io.riguron.game.winner.solo.order.Place;
import io.riguron.game.winner.solo.order.PlaceOrderByScore;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderedResultCalculationTest {

    private GamePlayerStorage<?> storage = mock(GamePlayerStorage.class);


    @Test
    public void getPlaces() {
        when(storage.getAllPlayers()).thenAnswer(x -> getPlayers());

        OrderedResultCalculation orderedResultCalculation = new OrderedResultCalculation(
                storage,
                new PlaceOrderByScore()
        );

        List<Place> places = orderedResultCalculation.getPlaces();

        assertEquals(7, places.size());

        assertPlace(places, 0, 61);
        assertPlace(places, 1, 11);
        assertPlace(places, 2, 9);
    }

    private void assertPlace(List<Place> places, int index, int expectedScore) {
        Assert.assertEquals(expectedScore, places.get(index).getGamePlayer().getScore().getValue());
    }

    private List<? super GamePlayer> getPlayers() {
        List<? super GamePlayer> players = new ArrayList<>();
        players.add(newGamePlayer(5));
        players.add(newGamePlayer(8));
        players.add(newGamePlayer(9));
        players.add(newGamePlayer(61));
        players.add(newGamePlayer(3));
        players.add(newGamePlayer(1));
        players.add(newGamePlayer(11));
        return players;
    }

    private GamePlayer newGamePlayer(int score) {
        GamePlayer gamePlayer = mock(GamePlayer.class);
        when(gamePlayer.getScore()).thenAnswer(invocationOnMock -> {
            GameScore gameScore = mock(GameScore.class);
            when(gameScore.getValue()).thenReturn(score);
            return gameScore;
        });
        return gamePlayer;
    }

}
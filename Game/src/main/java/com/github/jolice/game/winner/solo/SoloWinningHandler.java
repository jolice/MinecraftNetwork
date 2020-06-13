package com.github.jolice.game.winner.solo;

import com.github.jolice.game.winner.solo.order.Place;
import lombok.RequiredArgsConstructor;
import com.github.jolice.game.player.GamePlayer;
import com.github.jolice.game.winner.WinningHandler;
import io.riguron.system.internalization.Message;
import io.riguron.system.stream.Broadcast;

import java.util.List;

@RequiredArgsConstructor
public class SoloWinningHandler implements WinningHandler {

    private static final int TOP_PLACES = 3;

    private final SoloResultCalculation resultCalculation;
    private final Broadcast broadcast;

    @Override
    public void gameFinished() {
        List<Place> places = resultCalculation.getPlaces();
        for (int i = 0; i < TOP_PLACES; i++) {
            GamePlayer gamePlayer = places.get(i).getGamePlayer();
            broadcast.broadcast(new Message("game.solo.win.places", i + 1, gamePlayer.getName()));
        }

    }


}

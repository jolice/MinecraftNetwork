package me.riguron.game.winner.solo;

import lombok.RequiredArgsConstructor;
import me.riguron.game.player.GamePlayer;
import me.riguron.game.winner.WinningHandler;
import me.riguron.game.winner.solo.order.Place;
import me.riguron.system.internalization.Message;
import me.riguron.system.stream.Broadcast;

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

package com.github.jolice.game.player;

import com.github.jolice.game.kit.Kit;
import org.bukkit.Location;

import java.util.UUID;

/**
 * Null-object (special case) pattern.
 */
public class NullPlayer implements GamePlayer {

    private final GamePlayerStatus playerStatus = new NullStatus();
    private final NullGameScore gameScore = new NullGameScore();
    private final GamePlayerKit playerKit = new NullKit();

    @Override
    public String getName() {
        return "[NullPlayer]";
    }

    @Override
    public void teleport(Location location) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GamePlayerStatus getStatus() {
        return playerStatus;
    }

    @Override
    public GameScore getScore() {
        return gameScore;
    }

    @Override
    public UUID getId() {
        throw new UnsupportedOperationException();
    }

    @Override
    public GamePlayerKit getKit() {
        return playerKit;
    }

    private static class NullStatus implements GamePlayerStatus {

        @Override
        public void dropOut() {
        }

        @Override
        public void setSpectator(boolean spectator) {
        }

        @Override
        public boolean isAlive() {
            return false;
        }

        @Override
        public boolean isSpectator() {
            return true;
        }

        @Override
        public int getDropOutTime() {
            return 0;
        }
    }

    private static class NullKit extends GamePlayerKit {
        @Override
        public Kit getCurrentKit() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void setCurrentKit(Kit currentKit) {
            throw new UnsupportedOperationException();
        }
    }

    private static class NullGameScore extends GameScore {
        @Override
        public int getValue() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void setValue(int value) {
            throw new UnsupportedOperationException();
        }
    }
}

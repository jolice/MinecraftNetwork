package me.riguron.game.team;

import org.bukkit.DyeColor;
import me.riguron.game.player.GameScore;
import me.riguron.game.player.team.TeamPlayer;
import me.riguron.game.team.add.TeamAddResult;

import java.util.Collections;
import java.util.Set;

public enum NullTeam implements Team {

    INSTANCE;

    private final GameScore gameScore = new GameScore();

    @Override
    public DyeColor getColor() {
        return DyeColor.BLACK;
    }

    @Override
    public String getName() {
        return "None";
    }

    @Override
    public TeamPlayers getPlayers() {
        return new TeamPlayers() {
            @Override
            public Set<TeamPlayer> list() {
                return Collections.emptySet();
            }

            @Override
            public int count() {
                return 0;
            }

            @Override
            public int max() {
                return 0;
            }

            @Override
            public long alive() {
                return 0;
            }

            @Override
            public TeamAddResult add(TeamPlayer player) {
                throw new UnsupportedOperationException("Can't add to this team");
            }

            @Override
            public boolean remove(TeamPlayer gamePlayer) {
                return true;
            }
        };
    }

    @Override
    public boolean isAlive() {
        return false;
    }


    @Override
    public GameScore getScore() {
        return gameScore;
    }


}

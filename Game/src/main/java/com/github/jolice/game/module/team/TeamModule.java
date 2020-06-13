package com.github.jolice.game.module.team;

import com.github.jolice.game.core.Game;
import com.github.jolice.game.listener.dropout.TeamDropOutListener;
import com.github.jolice.game.listener.start.TeamGameStartListener;
import com.github.jolice.game.listener.state.StateDependentListener;
import com.github.jolice.game.listener.team.TeamQuitListener;
import com.github.jolice.game.map.GameMap;
import com.github.jolice.game.player.GamePlayer;
import com.github.jolice.game.player.factory.GamePlayerFactory;
import com.github.jolice.game.player.factory.TeamPlayerFactory;
import com.github.jolice.game.player.repository.GamePlayerStorage;
import com.github.jolice.game.player.repository.ValidatingGamePlayerStorage;
import com.github.jolice.game.player.repository.VirtualGamePlayerStorage;
import com.github.jolice.game.player.team.NullTeamPlayer;
import com.github.jolice.game.player.team.TeamPlayer;
import com.github.jolice.game.team.client.TeamAssigner;
import com.github.jolice.game.team.client.TeamLoader;
import com.github.jolice.game.team.repository.InMemoryTeams;
import com.github.jolice.game.team.repository.Teams;
import com.github.jolice.game.winner.WinningHandler;
import com.github.jolice.game.winner.team.ScoreTeamResultCalculation;
import com.github.jolice.game.winner.team.TeamResultCalculation;
import com.github.jolice.game.winner.team.TeamWinningHandler;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.multibindings.ProvidesIntoSet;
import com.github.jolice.config.client.ConfigurationLoader;
import com.github.jolice.game.team.selector.TeamGuiListener;
import com.github.jolice.game.team.selector.TeamSelectorInventory;
import com.github.jolice.game.config.team.TeamGameConfig;
import io.riguron.system.stream.Broadcast;
import org.bukkit.Server;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;


public class TeamModule extends AbstractModule {

    @Provides
    @Singleton
    public GamePlayerStorage<TeamPlayer> gamePlayerRepository() {
        return new ValidatingGamePlayerStorage<>(new VirtualGamePlayerStorage<>(new NullTeamPlayer()));
    }

    @Provides
    @Singleton
    public GamePlayerStorage<?> genericPlayerRepository(GamePlayerStorage<TeamPlayer> teamPlayerRepository) {
        return teamPlayerRepository;
    }

    @Provides
    @Singleton
    @SuppressWarnings("unchecked")
    public GamePlayerStorage<? super GamePlayer> gamePlayerStorage(GamePlayerStorage<?> storage) {
        return (GamePlayerStorage<? super GamePlayer>) storage;
    }

    @ProvidesIntoSet
    @Singleton
    public Listener teamDropOutListener(Game game, Teams teams, GamePlayerStorage<TeamPlayer> gamePlayerStorage) {
        return new TeamDropOutListener(game, teams, gamePlayerStorage);
    }

    @Provides
    @Singleton
    public TeamGameConfig gameOptions(ConfigurationLoader configurationLoader) {
        return configurationLoader.load("game", TeamGameConfig.class);
    }

    @Provides
    @Singleton
    public TeamLoader teamLoader(TeamGameConfig teamGameConfig, Teams teams) {
        TeamLoader teamLoader = new TeamLoader(teamGameConfig, teams);
        teamLoader.loadTeams();
        return teamLoader;
    }

    @Provides
    @Singleton
    public List<GameMap> teamMaps(ConfigurationLoader configurationLoader, GamePlayerStorage<TeamPlayer> teamPlayerRepository, Teams teams) {
        return new TeamMapsLoader(configurationLoader, teamPlayerRepository, teams).loadTeamMaps();
    }

    @Provides
    @Singleton
    public GamePlayerFactory<? extends GamePlayer> gamePlayerFactory() {
        return new TeamPlayerFactory();
    }

    @Provides
    @Singleton
    public Teams teamRepository() {
        return new InMemoryTeams();
    }

    @ProvidesIntoSet
    @Singleton
    public StateDependentListener teamGuiListener(GamePlayerStorage<TeamPlayer> gamePlayerStorage, TeamSelectorInventory teamSelectorInventory) {
        return new TeamGuiListener(gamePlayerStorage, teamSelectorInventory);
    }

    @Provides
    @Singleton
    public TeamSelectorInventory teamSelectorInventory(Teams teams, Server server) {
        return new TeamSelectorInventory(new ArrayList<>(teams.getAllTeams()), server);
    }

    @ProvidesIntoSet
    @Singleton
    public Listener gameStartListener(TeamAssigner teamAssigner) {
        return new TeamGameStartListener(teamAssigner);
    }

    @Provides
    @Singleton
    public TeamAssigner teamAssigner(GamePlayerStorage<TeamPlayer> gamePlayerStorage, Teams teams) {
        return new TeamAssigner(gamePlayerStorage, teams);
    }

    @Provides
    @Singleton
    public StateDependentListener teamQuitListener(GamePlayerStorage<TeamPlayer> gamePlayerStorage) {
        return new TeamQuitListener(gamePlayerStorage);
    }

    @Provides
    @Singleton
    public TeamResultCalculation teamResultCalculation(Teams teams) {
        return new ScoreTeamResultCalculation(teams);
    }

    @Provides
    @Singleton
    public WinningHandler winningHandler(TeamResultCalculation teamResultCalculation, Broadcast broadcast) {
        return new TeamWinningHandler(teamResultCalculation, broadcast);
    }
}

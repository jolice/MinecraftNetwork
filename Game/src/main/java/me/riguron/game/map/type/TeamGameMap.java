package me.riguron.game.map.type;

import me.riguron.game.player.repository.GamePlayerStorage;
import me.riguron.game.player.team.TeamPlayer;
import me.riguron.game.team.Team;
import org.bukkit.Location;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class TeamGameMap extends AbstractGameMap {

    private final GamePlayerStorage<TeamPlayer> teamPlayerRepository;
    private Map<Team, List<Location>> configuredTeamLocations;
    private Map<Team, Iterator<Location>> teleportLocations = new HashMap<>();

    public TeamGameMap(String name, GamePlayerStorage<TeamPlayer> teamPlayerRepository, Map<Team, List<Location>> configuredTeamLocations) {
        super(name);
        this.teamPlayerRepository = teamPlayerRepository;
        this.configuredTeamLocations = configuredTeamLocations;
    }

    @Override
    public void teleportPlayers() {

        teamPlayerRepository
                .getAllPlayers()
                .forEach(teamPlayer -> teamPlayer.teleport(
                        this.validLocationIterator(teamPlayer).next()));
    }

    @Override
    public void teleportPlayer(UUID playerId) {
        TeamPlayer player = teamPlayerRepository.getPlayer(playerId);
        List<Location> locations = computeLocationsForTeam(player.getTeam());
        player.teleport(locations.get(ThreadLocalRandom.current().nextInt(locations.size())));
    }


    private Iterator<Location> validLocationIterator(TeamPlayer teamPlayer) {
        this.ensurePlayerHasTeam(teamPlayer);
        Iterator<Location> locationIterator = teleportLocations.computeIfAbsent(teamPlayer.getTeam(), this::iteratorForTeam);
        if (!locationIterator.hasNext()) {
            throw new IllegalStateException("No location found for player");
        }

        return locationIterator;
    }

    private Iterator<Location> iteratorForTeam(Team team) {
        return this.computeLocationsForTeam(team).iterator();
    }

    private List<Location> computeLocationsForTeam(Team team) {
        return configuredTeamLocations.compute(team, (name, locations) -> {
            if (locations == null || locations.isEmpty()) {
                throw new IllegalArgumentException("No spawns found for team " + name);
            }
            return locations;
        });
    }


    private void ensurePlayerHasTeam(TeamPlayer teamPlayer) {
        if (teamPlayer.isInTeam()) {
            throw new IllegalStateException("Player " + teamPlayer.getName() + " does not have team assigned");
        }
    }


}

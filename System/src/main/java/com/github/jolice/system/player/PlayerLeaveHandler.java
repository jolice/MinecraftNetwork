package com.github.jolice.system.player;

import com.github.jolice.system.punishment.repository.PunishmentDataRepository;
import io.ebean.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import com.github.jolice.system.task.TaskFactory;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

/**
 * This class is responsible for handling of players' disconnection.
 */
@RequiredArgsConstructor
public class PlayerLeaveHandler {

    private final PunishmentDataRepository punishmentDataRepository;
    private final PlayerProfileRepository profileRepository;
    private final TaskFactory taskFactory;

    /**
     * Asynchronously handles player's disconnection.
     * Clients of this method are safe to run it in a main server thread.
     *
     * @param leftPlayer player that leaves the server
     */
    public void onLeave(UUID leftPlayer) {
        taskFactory.newAsyncTask(() -> {
            updateAfterLeave(leftPlayer);
            invalidateRepositories(leftPlayer);
        }).execute();
    }

    /**
     * Updates the data of player that leaves the server.
     *
     * @param leftPlayer id of leaving player
     */
    @Transactional
    private void updateAfterLeave(UUID leftPlayer) {
        final PlayerProfile playerProfile = profileRepository.get(leftPlayer);
        playerProfile.setPlayTime(playerProfile.getPlayTime() +
                ChronoUnit.MILLIS.between(LocalDateTime.now(), playerProfile.getLastLogin()));
        playerProfile.setLastLogin(LocalDateTime.now());
        profileRepository.update(playerProfile);
    }

    /**
     * Removes any cached data associated with the player.
     *
     * @param leftPlayer ID of a player that left the player.
     */
    private void invalidateRepositories(UUID leftPlayer) {
        profileRepository.invalidate(leftPlayer);
        punishmentDataRepository.invalidate(leftPlayer);
    }
}

package com.github.jolice.system.player;

import com.github.jolice.system.player.specification.UUIDSpecification;
import com.github.jolice.system.rank.Ranks;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.github.jolice.system.login.LoginDetails;
import com.github.jolice.system.login.chain.LoginChainLink;
import com.github.jolice.system.login.chain.LoginProcessingException;

import javax.persistence.PersistenceException;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * LoginChain link responsible for loading players' data before they join the server.
 */
@RequiredArgsConstructor
@Slf4j
public class PlayerProfileLoader extends LoginChainLink {

    private final PlayerProfileRepository playerProfileRepository;
    private final Ranks ranks;


    @Override
    public void proceed(LoginDetails loginDetails) {
        try {
            log.info("Processing {}", loginDetails.getName());
            attemptProcessing(loginDetails);
            super.proceedNext(loginDetails);
        } catch (PersistenceException e) {
            throw new LoginProcessingException(e);
        }
    }

    /**
     * Attempts to load data of existing player, or registers new one if that player
     * had not joined a server before.
     *
     * @param loginDetails details of player being logged in
     */
    private void attemptProcessing(LoginDetails loginDetails) {
        Optional<PlayerProfile> newPlayer = playerProfileRepository.find(new UUIDSpecification(loginDetails.getUuid()), PlayerAssociation.PREFERENCES, PlayerAssociation.PURCHASES);
        PlayerProfile target;
        if (newPlayer.isPresent()) {
            target = newPlayer.get();
            this.processExistingPlayer(target, loginDetails.getName());
        } else {
            target = this.saveNewProfile(loginDetails);
        }
        playerProfileRepository.put(target.getUuid(), target);
    }

    /**
     * Processes existing player and puts corresponding entity into the cache.
     *
     * @param playerProfile existing profile
     * @param name          name of the player being processed
     */
    private void processExistingPlayer(PlayerProfile playerProfile, String name) {
        log.info("Processing existing profile '{}'", name);
        if (!playerProfile.getName().equals(name)) {
            playerProfile.setName(name);
        }
        playerProfile.setRank(ranks.getRankSingleton(playerProfile.getRank().getId()));
        playerProfile.setLastLogin(LocalDateTime.now());
        playerProfileRepository.update(playerProfile);
    }

    /**
     * Creates new database record associated with the joined player.
     *
     * @param loginDetails details of the player
     * @return entity record representing connected player
     */
    private PlayerProfile saveNewProfile(LoginDetails loginDetails) {
        log.info("Saving new profile '{}'", loginDetails.getName());
        PlayerProfile playerProfile = new PlayerProfile(loginDetails.getUuid(), loginDetails.getName());
        playerProfile.setRank(ranks.getDefaultRank());
        playerProfileRepository.save(playerProfile);
        return playerProfile;
    }

}

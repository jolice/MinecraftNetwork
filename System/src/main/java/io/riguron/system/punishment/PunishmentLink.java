package io.riguron.system.punishment;

import io.riguron.system.login.LoginDetails;
import io.riguron.system.punishment.model.ActivePunishmentType;
import io.riguron.system.punishment.repository.PunishmentDataRepository;
import lombok.RequiredArgsConstructor;
import io.riguron.system.login.LoginDetails;
import io.riguron.system.login.chain.LoginChainLink;
import io.riguron.system.login.chain.LoginProcessingException;
import io.riguron.system.punishment.model.ActivePunishmentType;
import io.riguron.system.punishment.repository.PunishmentDataRepository;

/**
 * LoginChain link responsible for checking player's punishment. If player is banned,
 * he gets disconnected from the server with an appropriate disconnect message that describes
 * player's ban reason.
 */
@RequiredArgsConstructor
public class PunishmentLink extends LoginChainLink {

    private final PunishmentChecker punishmentChecker;
    private final PunishmentDataRepository repository;

    @Override
    protected void proceed(LoginDetails loginDetails) {
        this.load(loginDetails);
        this.checkBanned(loginDetails);
        super.proceedNext(loginDetails);
    }

    private void checkBanned(LoginDetails loginDetails) {
        punishmentChecker
                .checkPunishment(loginDetails.getUuid(), ActivePunishmentType.BAN)
                .ifPresent(activePunishmentRecord -> {
                    repository.invalidate(loginDetails.getUuid());
                    throw new LoginProcessingException("You are banned! Reason: " + activePunishmentRecord.getReason());
                });
    }

    private void load(LoginDetails loginDetails) {
        repository
                .findPunishmentData(loginDetails.getUuid())
                .ifPresent(punishmentData -> repository.put(punishmentData.getId(), punishmentData));
    }
}

package io.riguron.system.dialog.ignore;

import io.riguron.system.player.PlayerProfileRepository;
import lombok.RequiredArgsConstructor;
import io.riguron.system.player.PlayerProfileRepository;
import io.riguron.system.player.specification.PlayerNameSpecification;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class IgnoreService {

    private static final int RECORDS_PER_PAGE = 5;

    private final PlayerProfileRepository playerProfileRepository;
    private final IgnoreRepository ignoreRepository;

    /**
     * Creates new ignore record.
     *
     * @param who    the player who ignores target
     * @param target the player being ignored
     * @return result indicating whether action is performed successfully
     */
    public IgnoreResult ignore(UUID who, String target) {
        return playerProfileRepository
                .find(new PlayerNameSpecification(target))
                .map(profile -> ignoreRepository.ignore(who, profile) ? IgnoreResult.IGNORED : IgnoreResult.ALREADY_IGNORING)
                .orElse(IgnoreResult.NOT_FOUND);
    }

    /**
     * Removes ignore record, i.e a player being ignored is no longer restricted to send private messages to a
     * record creator.
     *
     * @param who    the player who ignores target
     * @param target the player being ignored
     * @return result indicating whether action is performed successfully
     */
    public IgnoreRemoveResult removeIgnore(UUID who, String target) {
        return ignoreRepository.removeIgnore(who, target) ? IgnoreRemoveResult.REMOVED_IGNORE : IgnoreRemoveResult.NOT_IGNORING;
    }

    /**
     * Retrieves the history of ignore record for the given player.
     *
     * @param who  author of record history
     * @param page page of the history to be displayed
     * @return specified page of history of ignore records created by the given player
     */
    public List<IgnoreHistoryElement> getHistory(UUID who, int page) {
        return ignoreRepository.getIgnoreHistory(who, page, RECORDS_PER_PAGE);
    }

    /**
     * Checks whether the player ignores other one.
     *
     * @param who who ignores
     * @param target the player being checked
     * @return whether "who" ignores "target"
     */
    public boolean isIgnoring(UUID who, String target) {
        return ignoreRepository.isIgnoring(who, target);
    }
}

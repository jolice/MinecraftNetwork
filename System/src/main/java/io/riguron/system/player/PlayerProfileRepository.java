package io.riguron.system.player;

import io.riguron.persistence.CachingRepository;
import io.riguron.persistence.specification.Specification;

import java.util.Optional;
import java.util.UUID;

/**
 * Cache-capable player repository. Non-inherited methods of this interface
 * are not cache-aware, i.e do not interact with the cache in any manner. Saving, updating
 * or querying will not hit the cache. Caching must be explicitly performed using inherited methods.
 */
public interface PlayerProfileRepository extends CachingRepository<UUID, PlayerProfile> {

    /**
     * Finds a player entity by the specified specification and fetches specified associations eagerly.
     * It means that specified associations will be loaded in a single database round-trip. Associations
     * that are not specified will be fetched lazily.
     *
     * @param specification      specification for the query
     * @param playerAssociations associations of player entity that will be fetched eagerly.
     * @return non-cached player entity
     */
    Optional<PlayerProfile> find(Specification specification, PlayerAssociation... playerAssociations);

    void save(PlayerProfile playerProfile);

    void update(PlayerProfile playerProfile);

    /**
     * Finds player's UUID by the provided specification.
     *
     * @param specification search query
     * @return UUID of a player found by the specification, or empty optional if there is no one
     */
    Optional<UUID> findId(Specification specification);

}

package com.github.jolice.system.player;

import com.github.jolice.system.exception.PlayerNotFoundException;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import io.ebean.EbeanServer;
import io.ebean.Query;
import lombok.RequiredArgsConstructor;
import com.github.jolice.persistence.specification.Specification;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class CachingPlayerRepository implements PlayerProfileRepository {

    private final Cache<UUID, PlayerProfile> cache = CacheBuilder.newBuilder().maximumSize(1000).build();
    private final EbeanServer ebeanServer;

    @Override
    public PlayerProfile get(UUID uuid) {
        PlayerProfile existing = cache.getIfPresent(uuid);
        if (existing == null) {
            throw new PlayerNotFoundException();
        }
        return existing;
    }

    @Override
    public void put(UUID uuid, PlayerProfile playerProfile) {
        this.cache.put(uuid, playerProfile);
    }

    @Override
    public Optional<PlayerProfile> find(Specification specification, PlayerAssociation... playerAssociations) {
       Query<PlayerProfile> query = specification.append(ebeanServer.find(PlayerProfile.class));
        for (PlayerAssociation playerAssociation : playerAssociations) {
            query = query.fetch(playerAssociation.getPath());
        }
        return query.findOneOrEmpty();
    }

    @Override
    public void invalidate(UUID id) {
        cache.invalidate(id);
    }

    @Override
    public void save(PlayerProfile playerProfile) {
        ebeanServer.save(playerProfile);
    }

    @Override
    public void update(PlayerProfile playerProfile) {
        ebeanServer.update(playerProfile);
    }

    @Override
    public Optional<UUID> findId(Specification specification) {
        return Optional.ofNullable(specification.append(ebeanServer.find(PlayerProfile.class))
                .select("uuid")
                .findSingleAttribute());
    }


}

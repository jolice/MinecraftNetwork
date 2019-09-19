package io.riguron.system.punishment.repository;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import io.ebean.EbeanServer;
import io.riguron.system.punishment.model.PunishmentData;
import lombok.RequiredArgsConstructor;
import io.riguron.system.punishment.model.PunishmentData;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class PunishmentDataRepository {

    private final Cache<UUID, PunishmentData> cache = CacheBuilder.newBuilder().maximumSize(1000).build();
    private final EbeanServer ebeanServer;

    public Optional<PunishmentData> findPunishmentData(UUID uuid) {
        return ebeanServer.createQuery(PunishmentData.class)
                .fetch("activePunishments")
                .setId(uuid)
                .findOneOrEmpty();
    }

    public void put(UUID uuid, PunishmentData punishmentData) {
        cache.put(uuid, punishmentData);
    }

    public Optional<PunishmentData> getPunishmentData(UUID uuid) {
        return Optional.ofNullable(cache.getIfPresent(uuid));
    }

    public void update(PunishmentData punishmentData) {
        ebeanServer.update(punishmentData);
    }

    public void save(PunishmentData punishmentData) {
        ebeanServer.save(punishmentData);
    }

    public void invalidate(UUID uuid) {
        cache.invalidate(uuid);
    }
}

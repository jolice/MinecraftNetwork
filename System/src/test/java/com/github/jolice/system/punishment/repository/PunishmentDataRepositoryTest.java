package com.github.jolice.system.punishment.repository;

import com.github.jolice.system.player.PlayerProfile;
import com.github.jolice.system.punishment.model.ActivePunishmentRecord;
import com.github.jolice.system.punishment.model.ActivePunishmentType;
import com.github.jolice.system.punishment.model.PunishmentData;
import com.github.jolice.system.test.RepositoryTest;
import io.ebean.EbeanServer;
import org.junit.After;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class PunishmentDataRepositoryTest extends RepositoryTest<PunishmentDataRepository> {

    @Test
    public void findPunishmentData() {
        UUID uuid = UUID.randomUUID();
        PunishmentData punishmentData = new PunishmentData(uuid);
        PlayerProfile target = createProfile(uuid);
        punishmentData.addPunishment(ActivePunishmentType.MUTE,
                new ActivePunishmentRecord("punisher", target, "reason", LocalDateTime.now(),
                        ActivePunishmentType.MUTE));
        repository.save(punishmentData);

        final Optional<PunishmentData> fetchedData = repository.findPunishmentData(uuid);
        assertTrue(fetchedData.isPresent());
        fetchedData.ifPresent(pd -> assertTrue(
                pd.getPunishment(ActivePunishmentType.MUTE).isPresent()));
    }

    private PlayerProfile createProfile(UUID uuid) {
        PlayerProfile target = new PlayerProfile(uuid, "name");
        ebean.save(target);
        return target;
    }

    @Test
    public void put() {
        PunishmentData punishmentData = mock(PunishmentData.class);
        UUID uuid = UUID.randomUUID();
        repository.put(uuid, punishmentData);

        final Optional<PunishmentData> cachedDataOptional = repository.getPunishmentData(uuid);
        assertTrue(cachedDataOptional.isPresent());
        cachedDataOptional.ifPresent(cachedData -> assertEquals(cachedData, punishmentData));

    }

    @Test
    public void update() {

        UUID uuid = UUID.randomUUID();
        PunishmentData punishmentData = new PunishmentData(uuid);
        PlayerProfile target = createProfile(uuid);

        repository.save(punishmentData);
        Optional<PunishmentData> optionalData = repository.findPunishmentData(uuid);
        assertTrue(optionalData.isPresent());
        PunishmentData loadedData = optionalData.get();
        loadedData.addPunishment(ActivePunishmentType.BAN, new ActivePunishmentRecord(
                "punisher",
                target,
                "reason",
                LocalDateTime.now(),
                ActivePunishmentType.BAN
        ));

        repository.update(loadedData);

        Optional<PunishmentData> afterUpdateOptional = repository.findPunishmentData(uuid);
        assertTrue(afterUpdateOptional.isPresent());
        afterUpdateOptional.ifPresent(pd -> assertTrue(
                pd.getPunishment(ActivePunishmentType.BAN).isPresent()));
    }

    @Override
    protected PunishmentDataRepository createRepository(EbeanServer server) {
        return new PunishmentDataRepository(ebean);
    }

    @After
    public void cleanUp() {
        ebean.find(PunishmentData.class).delete();
    }
}
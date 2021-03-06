package com.github.jolice.system.punishment.repository;

import com.github.jolice.system.player.PlayerProfile;
import com.github.jolice.system.punishment.model.PunishmentRecord;
import com.github.jolice.system.punishment.model.type.PunishmentType;
import com.github.jolice.system.test.RepositoryTest;
import io.ebean.EbeanServer;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PunishmentRepositoryTest extends RepositoryTest<PunishmentRepository> {

    @Test
    public void savePunishment() {
        final UUID uuid = UUID.randomUUID();
        PlayerProfile target = new PlayerProfile(uuid, "Name");
        ebean.save(target);

        PunishmentRecord warn = new PunishmentRecord("punisher", target, "warn reason", PunishmentType.WARN);
        PunishmentRecord kick = new PunishmentRecord("punisher", target, "kick reason", PunishmentType.KICK);
        PunishmentRecord ban = new PunishmentRecord("punisher", target, "ban reason", PunishmentType.BAN);

        repository.savePunishment(warn);
        repository.savePunishment(kick);
        repository.savePunishment(ban);

        List<PunishmentRecord> punishments =
                repository.getPunishments(uuid, 0, 10);
        assertEquals(3, punishments.size());

        assertTrue(punishments.containsAll(Arrays.asList(warn, kick, ban)));
    }

    @Override
    protected PunishmentRepository createRepository(EbeanServer server) {
        return new PunishmentRepository(server);
    }
}
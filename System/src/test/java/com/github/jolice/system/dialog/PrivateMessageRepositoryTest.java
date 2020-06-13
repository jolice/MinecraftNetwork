package com.github.jolice.system.dialog;

import com.github.jolice.system.player.PlayerProfile;
import com.github.jolice.system.test.RepositoryTest;
import io.ebean.EbeanServer;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class PrivateMessageRepositoryTest extends RepositoryTest<PrivateMessageRepository> {

    @Test
    public void save() {

        List<PlayerProfile> profiles = createProfiles();

        PrivateMessageRecord record = new PrivateMessageRecord(
                profiles.get(0), profiles.get(1), "text"
        );

        repository.save(record);
    }


    @Test
    public void getHistory() {

        List<PlayerProfile> profiles = createProfiles();

        for (int i = 0; i < 10; i++) {
            PrivateMessageRecord record = new PrivateMessageRecord(
                    profiles.get(0), profiles.get(1), "text-" + i
            );
            repository.save(record);
        }

        PrivateMessageRecord aC = new PrivateMessageRecord(profiles.get(0), profiles.get(2), "a to c");
        PrivateMessageRecord bC = new PrivateMessageRecord(profiles.get(1), profiles.get(2), "a to c");

        repository.save(aC);
        repository.save(bC);

        final List<PrivateMessageProjection> history = repository.getHistory(profiles.get(0).getUuid(), profiles.get(1).getName(), 0, 10);

        assertEquals(10, history.size());
       history.sort(Comparator.comparing(PrivateMessageProjection::getText));

        for (int i = 0; i < history.size(); i++) {
            final PrivateMessageProjection privateMessageProjection = history.get(i);
            assertEquals("text-" + i, privateMessageProjection.getText());
        }
    }

    private List<PlayerProfile> createProfiles() {
        PlayerProfile a = new PlayerProfile(UUID.randomUUID(), "a");
        PlayerProfile b = new PlayerProfile(UUID.randomUUID(), "b");
        PlayerProfile c = new PlayerProfile(UUID.randomUUID(), "c");
        List<PlayerProfile> playerProfiles = Arrays.asList(a, b, c);
        ebean.saveAll(playerProfiles);
        return playerProfiles;
    }


    @Override
    protected PrivateMessageRepository createRepository(EbeanServer server) {
        return new PrivateMessageRepository(ebean);
    }
}
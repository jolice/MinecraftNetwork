package me.riguron.system.dialog.ignore;

import io.ebean.EbeanServer;
import me.riguron.system.player.PlayerProfile;
import me.riguron.system.test.RepositoryTest;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

public class IgnoreRepositoryTest extends RepositoryTest<IgnoreRepository> {

    private static final String NAME = "name";

    @Test
    public void isIgnoring() {
        UUID me = UUID.randomUUID();
        PlayerProfile playerProfile = addProfile();
        repository.ignore(me, playerProfile);
        assertTrue(repository.isIgnoring(me, NAME));
    }

    @Test
    public void ignore() {
        UUID me = UUID.randomUUID();
        PlayerProfile playerProfile = addProfile();
        assertTrue(repository.ignore(me, playerProfile));
        assertFalse(repository.ignore(me, playerProfile));
    }

    @Test
    public void ignoreHistory() {
        UUID me = UUID.randomUUID();
        for (int i = 0; i < 5; i++) {
            PlayerProfile profile = addProfile("Rec-" + i);
            repository.ignore(me, profile);
        }

        List<IgnoreHistoryElement> ignoreHistory = repository.getIgnoreHistory(me, 0, 10);
        assertEquals(5, ignoreHistory.size());
        for (int i = 0; i < ignoreHistory.size(); i++) {
            IgnoreHistoryElement ignoreHistoryElement = ignoreHistory.get(i);
            assertEquals("Rec-" + i, ignoreHistoryElement.getTarget());
        }
    }

    @Test
    public void removeIgnore() {
        PlayerProfile playerProfile = addProfile();
        UUID me = UUID.randomUUID();
        repository.ignore(me, playerProfile);
        assertTrue(repository.isIgnoring(me, NAME));
        repository.removeIgnore(me, NAME);
        assertFalse(repository.isIgnoring(me, NAME));
    }

    private PlayerProfile addProfile() {
        return addProfile(NAME);
    }

    private PlayerProfile addProfile(String name) {
        UUID target = UUID.randomUUID();
        PlayerProfile playerProfile = new PlayerProfile(target, name);
        ebean.save(playerProfile);
        return playerProfile;
    }


    @Override
    protected IgnoreRepository createRepository(EbeanServer server) {
        return new IgnoreRepository(ebean);
    }

}
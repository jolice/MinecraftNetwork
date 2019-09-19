package io.riguron.system.preferences;

import io.ebean.EbeanServer;
import io.riguron.system.player.PlayerProfile;
import io.riguron.system.preferences.specification.PlayerNameSpecification;
import io.riguron.system.test.RepositoryTest;
import io.riguron.system.player.PlayerProfile;
import io.riguron.system.preferences.specification.PlayerNameSpecification;
import io.riguron.system.test.RepositoryTest;
import org.junit.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PreferencesRepositoryTest extends RepositoryTest<PreferencesRepository> {

    @Test
    public void whenFindByParentProfile() {
        PlayerProfile profile = new PlayerProfile(
                UUID.randomUUID(), "name"
        );
        ebean.save(profile);
        final Optional<PlayerPreferences> optionalResult = repository.findBy(
                new PlayerNameSpecification("name")
        );
        assertTrue(optionalResult.isPresent());
        assertEquals(profile.getPlayerPreferences(), optionalResult.get());
    }

    @Override
    protected PreferencesRepository createRepository(EbeanServer server) {
        return new PreferencesRepository(ebean);
    }
}
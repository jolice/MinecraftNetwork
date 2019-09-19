package io.riguron.system.preferences;

import io.ebean.EbeanServer;
import lombok.RequiredArgsConstructor;
import io.riguron.persistence.specification.Specification;

import java.util.Optional;

@RequiredArgsConstructor
public class PreferencesRepository {

    private final EbeanServer ebeanServer;

    public Optional<PlayerPreferences> findBy(Specification specification) {
        return specification.append(ebeanServer.find(PlayerPreferences.class)).findOneOrEmpty();
    }

    public void update(PlayerPreferences playerPreferences) {
        ebeanServer.update(playerPreferences);
    }
}

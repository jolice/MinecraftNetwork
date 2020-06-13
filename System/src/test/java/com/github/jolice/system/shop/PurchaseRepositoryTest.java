package com.github.jolice.system.shop;

import com.github.jolice.system.player.PlayerAssociation;
import com.github.jolice.system.player.PlayerProfile;
import com.github.jolice.system.test.RepositoryTest;
import io.ebean.EbeanServer;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class PurchaseRepositoryTest extends RepositoryTest<PurchaseRepository> {

    @Test
    public void update() {
        PlayerProfile profile = new PlayerProfile(UUID.randomUUID(), "name");
        ebean.save(profile);

        assertNotNull(profile);
        profile.getPurchases().addPurchase(1, new Purchase(10, 1, "Desc", profile));

        runAndAssertQueries(3, () -> repository.update(profile.getPurchases()));

        PlayerProfile afterUpdate = ebean.find(PlayerProfile.class).setId(profile.getId()).fetch(
                PlayerAssociation.PURCHASES.getPath()).findOne();


        assertNotNull(afterUpdate);
        assertTrue(afterUpdate.getPurchases().hasPurchase(1));

    }

    @Override
    protected PurchaseRepository createRepository(EbeanServer server) {
        return new PurchaseRepository(server);
    }
}
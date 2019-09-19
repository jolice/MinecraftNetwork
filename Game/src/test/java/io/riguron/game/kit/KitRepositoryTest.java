package io.riguron.game.kit;

import io.ebean.EbeanServer;
import io.riguron.bukkit.item.entity.InventoryItemEntity;
import io.riguron.bukkit.item.entity.ItemStackEntity;
import io.riguron.game.kit.permissions.AnyoneKitPermissions;
import io.riguron.game.kit.permissions.KitPermissions;
import io.riguron.system.test.RepositoryTest;
import org.bukkit.Material;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class KitRepositoryTest extends RepositoryTest<KitRepository> {

    @Test
    public void findKitsByGameName() {
        KitPermissions kitPermissions = new AnyoneKitPermissions();
        ebean.save(kitPermissions);
        Kit kit = new Kit(

                new KitInformation(
                        "BedWars",
                        100L,
                        "Super kit",
                        Material.ARROW,
                        false
                ),
                kitPermissions,
                Collections.singletonList(
                        new InventoryItemEntity(
                                new ItemStackEntity(
                                        Material.DIAMOND_PICKAXE,
                                        "Some name",
                                        Collections.singletonList("List")
                                ),
                                1
                        )
                ),
                Arrays.asList("Line 1", "Line 2")
        );

        repository.save(kit);

        List<Kit> kitsForBedwars = repository.findKitsByGameName("BedWars");
        assertEquals(1, kitsForBedwars.size());
        assertEquals(kit, kitsForBedwars.get(0));
    }

    @Override
    protected KitRepository createRepository(EbeanServer server) {
        return new KitRepository(server);
    }
}
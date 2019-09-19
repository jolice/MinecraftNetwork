package io.riguron.bukkit.item;

import io.ebean.EbeanServer;
import io.riguron.bukkit.item.entity.ItemStackEntity;
import io.riguron.bukkit.item.entity.ItemStackEntity;
import io.riguron.system.test.EbeanTesting;
import org.bukkit.Material;
import org.junit.Test;

import java.util.Arrays;

public class ItemStackEntityTest {

    @Test
    public void tryPersist() {
        EbeanServer ebeanServer = new EbeanTesting().getEbeanServer();
        ItemStackEntity entity = new ItemStackEntity(
                Material.ARROW,
                "DisplayName",
                Arrays.asList("a", "b", "c")
        );
        ebeanServer.save(entity);
    }

}
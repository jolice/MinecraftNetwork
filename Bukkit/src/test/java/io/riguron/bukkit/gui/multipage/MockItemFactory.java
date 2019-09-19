package io.riguron.bukkit.gui.multipage;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.meta.ItemMeta;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockItemFactory {

    public static ItemFactory mockItemFactory() {
        PowerMockito.mockStatic(Bukkit.class);
        when(Bukkit.getItemFactory()).thenReturn(Mockito.mock(ItemFactory.class));
        when(Bukkit.getItemFactory().getItemMeta(any())).thenReturn(mock(ItemMeta.class));
        return Bukkit.getItemFactory();
    }
}

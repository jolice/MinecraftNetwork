package com.github.jolice.gui.multipage;

import com.github.jolice.gui.multipage.player.PlayerPages;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.junit.Test;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PageSwitcherTest {

    @Test
    public void switchPage() {
        InventoryPages inventoryPages = mock(InventoryPages.class);
        PlayerPages playerPages=  mock(PlayerPages.class);
        Server server = mock(Server.class);
        PageSwitcher pageSwitcher = new PageSwitcher(
                inventoryPages,playerPages,server
        );


        UUID uuid = UUID.randomUUID();
        Player player = mock(Player.class);
        when(server.getPlayer(eq(uuid))).thenReturn(player);
        when(playerPages.switchPage(eq(uuid), any())).thenReturn(5);

        Inventory inventory = mock(Inventory.class);
        when(inventoryPages.getPage(eq(5))).thenReturn(inventory);

        pageSwitcher.switchPage(uuid, operand -> operand + 1);


        verify(player).openInventory(eq(inventory));


    }

}
package me.riguron.bukkit.gui.multipage;

import me.riguron.bukkit.gui.multipage.player.PlayerPages;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.junit.Test;

import java.util.UUID;
import java.util.function.IntUnaryOperator;

import static org.junit.Assert.*;
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
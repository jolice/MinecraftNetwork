package me.riguron.bukkit.gui.multipage;

import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.junit.Test;
import me.riguron.bukkit.gui.multipage.player.PlayerPageRearranger;
import me.riguron.bukkit.gui.multipage.player.PlayerPages;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class PlayerPageRearrangerTest {

    private PlayerPages playerPages = mock(PlayerPages.class);
    private InventoryPages inventoryPages = mock(InventoryPages.class);
    private PlayerPageRearranger playerPageRearranger;

    @Test
    public void rearrangeAfterRemoval() {
        Server server = mock(Server.class);
        playerPages = mock(PlayerPages.class);
        inventoryPages = mock(InventoryPages.class);
        playerPageRearranger = new PlayerPageRearranger(
                playerPages,
                inventoryPages,
                server
        );

        UUID playerUUID = UUID.randomUUID();
        when(server.getPlayer(any(UUID.class))).thenReturn(mock(Player.class));

        when(playerPages.rearrangeAfterRemoval(anyInt())).thenReturn(Collections.singleton(playerUUID));

        removeFirst(playerUUID);
        removeLast(playerUUID);
        removeMid(playerUUID);
    }

    private void removeFirst(UUID playerUUID) {
        // When removing first page, then set next first page to all players
        playerPageRearranger.rearrangeAfterRemoval(1);
        verify(playerPages).setPlayerPage(eq(playerUUID), eq(1));
    }

    private void removeLast(UUID playerUUID) {
        // Remove last page, must open the previous
        when(inventoryPages.getTotalPages()).thenReturn(5);
        playerPageRearranger.rearrangeAfterRemoval(5);
        verify(playerPages).setPlayerPage(eq(playerUUID), eq(4));
    }

    private void removeMid(UUID playerUUID) {
        // Remove page from middle, must open the next one

        playerPageRearranger.rearrangeAfterRemoval(2);
        verify(playerPages).setPlayerPage(eq(playerUUID), eq(3));
    }
}
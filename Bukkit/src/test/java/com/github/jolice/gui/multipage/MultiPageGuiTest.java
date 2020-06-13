package com.github.jolice.gui.multipage;

import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.junit.Test;
import com.github.jolice.gui.Executable;
import com.github.jolice.gui.item.InventoryItem;
import com.github.jolice.gui.multipage.player.PlayerPageRearranger;
import com.github.jolice.gui.multipage.player.PlayerPages;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.*;

public class MultiPageGuiTest {

    private final InventoryPages inventoryPages = mock(InventoryPages.class);
    private final SlotActions slotActions = mock(SlotActions.class);
    private final Server server = mock(Server.class);
    private final PlayerPages playerPages = mock(PlayerPages.class);
    private final PlayerPageRearranger playerPageRearranger = mock(PlayerPageRearranger.class);


    @Test
    public void displayTo() {
        MultiPageGui multiPageGui = new MultiPageGui(playerPages, inventoryPages, mock(SlotActions.class), mock(Server.class), playerPageRearranger);
        Player player = mock(Player.class);
        Inventory inventory = mock(Inventory.class);
        when(inventoryPages.getPage(eq(1))).thenReturn(inventory);
        multiPageGui.displayTo(player);
        verify(player).openInventory(same(inventory));
        verify(playerPages).setPlayerPage(any(), eq(1));
    }

    @Test
    public void clear() {
        MultiPageGui multiPageGui = new MultiPageGui(mock(PlayerPages.class), inventoryPages, mock(SlotActions.class), mock(Server.class), playerPageRearranger);
        multiPageGui.clear();
        verify(inventoryPages).clearAll();
    }

    @Test
    public void addButton() {
         when(inventoryPages.getPage(anyInt())).thenReturn(mock(Inventory.class));
        MultiPageGui multiPageGui = new MultiPageGui(mock(PlayerPages.class), inventoryPages, slotActions, mock(Server.class), playerPageRearranger);
        InventoryItem inventoryItem = mock(InventoryItem.class);
        multiPageGui.addButton(inventoryItem, mock(Executable.class));
        verify(slotActions).addSlotAction(eq(inventoryItem), any());
    }

    @Test
    public void addItem() {
        when(inventoryPages.getPage(anyInt())).thenReturn(mock(Inventory.class));
        MultiPageGui multiPageGui = new MultiPageGui(mock(PlayerPages.class), inventoryPages, mock(SlotActions.class), mock(Server.class), playerPageRearranger);

        Inventory inventory = mock(Inventory.class);
        when(inventoryPages.getPage(eq(1))).thenReturn(inventory);
        InventoryItem item = mock(InventoryItem.class);
        ItemStack itemStack = mock(ItemStack.class);
        when(item.getItemStack()).thenReturn(itemStack);
        when(item.getSlot()).thenReturn(5);
        when(item.getPage()).thenReturn(1);
        when(inventoryPages.getTotalPages()).thenReturn(0);

        multiPageGui.addItem(item);
        verify(inventoryPages).addNewPage();

        verify(inventory).setItem(eq(5), eq(itemStack));

    }


    @Test
    public void removeItem() {

        MultiPageGui multiPageGui = new MultiPageGui(mock(PlayerPages.class), inventoryPages, slotActions, server, playerPageRearranger);

        Inventory firstPage = mock(Inventory.class);
        Inventory secondPage = mock(Inventory.class);

        InventoryItem firstPageItem = makeItem(1, 2);
        InventoryItem secondPageItem = makeItem(2, 3);

        when(inventoryPages.getPage(eq(1))).thenReturn(firstPage);
        when(inventoryPages.getPage(eq(2))).thenReturn(secondPage);

        multiPageGui.addItem(firstPageItem);
        multiPageGui.addItem(secondPageItem);

        when(secondPage.getContents()).thenReturn(new ItemStack[0]);
        when(inventoryPages.isEmpty()).thenReturn(false);

        multiPageGui.removeItem(secondPageItem.getSlot(), secondPageItem.getPage());
        when(playerPages.getActiveSessions()).thenReturn(Collections.singleton(UUID.randomUUID()));
        Player player = mock(Player.class);
        when(server.getPlayer(any(UUID.class))).thenReturn(player);


        verify(slotActions).shiftAfterPageRemoval(eq(2));
        verify(playerPageRearranger).rearrangeAfterRemoval(eq(2));


    }


    private InventoryItem makeItem(int page, int slot) {
        InventoryItem inventoryItem = mock(InventoryItem.class);
        when(inventoryItem.getPage()).thenReturn(page);
        when(inventoryItem.getSlot()).thenReturn(slot);
        ItemStack itemStack = mock(ItemStack.class);
        when(inventoryItem.getItemStack()).thenReturn(itemStack);
        return inventoryItem;

    }
}
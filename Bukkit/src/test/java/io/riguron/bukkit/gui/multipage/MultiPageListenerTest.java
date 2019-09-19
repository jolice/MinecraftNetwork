package io.riguron.bukkit.gui.multipage;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import io.riguron.bukkit.gui.item.InventoryItem;
import io.riguron.bukkit.gui.multipage.player.PlayerPages;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.legacy.PowerMockRunner;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static io.riguron.bukkit.gui.multipage.MockItemFactory.mockItemFactory;
import static io.riguron.bukkit.gui.multipage.MultiPageButtons.NEXT_PAGE;
import static io.riguron.bukkit.gui.multipage.MultiPageButtons.PREVIOUS_PAGE;


@RunWith(PowerMockRunner.class)
@PrepareForTest(Bukkit.class)
public class MultiPageListenerTest {

    private PageSwitcher pageSwitcher;
    private MultiPageListener multiPageListener;

    @Before
    public void setUp() {
        MockItemFactory.mockItemFactory();
    }

    @Test
    public void inventoryClosed() {
        PlayerPages playerPages = mock(PlayerPages.class);
        SlotActions slotActions = mock(SlotActions.class);
        MultiPageListener multiPageListener = new MultiPageListener(
                mock(PageSwitcher.class),
                slotActions,
                playerPages
        );

        InventoryView inventoryView = mock(InventoryView.class);
        when(inventoryView.getPlayer()).thenReturn(mock(HumanEntity.class));
        InventoryCloseEvent inventoryCloseEvent = new InventoryCloseEvent(
                inventoryView
        );

        multiPageListener.inventoryClosed(inventoryCloseEvent);
        verify(playerPages).removePlayer(any());
    }

    @Test
    public void inventoryClicked() {

        PlayerPages playerPages = mock(PlayerPages.class);
        when(playerPages.hasSession(any())).thenReturn(true);
        pageSwitcher = mock(PageSwitcher.class);
        SlotActions slotActions = mock(SlotActions.class);

        UUID uuid = UUID.randomUUID();
        InventoryClickEvent event = mock(InventoryClickEvent.class);
        when(event.getWhoClicked()).thenAnswer(invocationOnMock -> {
            HumanEntity humanEntity = mock(HumanEntity.class);
            when(humanEntity.getUniqueId()).thenReturn(uuid);
            return humanEntity;
        });

        this.multiPageListener = new MultiPageListener(pageSwitcher, slotActions, playerPages);
        this.clickNextPage(event, uuid);
        this.clickPreviousPage(event, uuid);
        this.arbitraryClick(event, slotActions, uuid);
    }


    private void arbitraryClick(InventoryClickEvent event, SlotActions slotActions, UUID uuid) {
        ItemStack stack = mock(ItemStack.class);
        when(event.getCurrentItem()).thenReturn(stack);
        when(event.getSlot()).thenReturn(5);
        multiPageListener.inventoryClicked(event);
        verify(slotActions).executeSlotAction(eq(uuid), eq(5));
    }

    private void clickNextPage(InventoryClickEvent event, UUID uuid) {
        clickPage(event, uuid, NEXT_PAGE);
    }

    private void clickPreviousPage(InventoryClickEvent event, UUID uuid) {
        clickPage(event, uuid, PREVIOUS_PAGE);
    }

    private void clickPage(InventoryClickEvent event, UUID uuid, InventoryItem switchItem) {
        when(event.getCurrentItem()).thenReturn(switchItem.getItemStack());
        multiPageListener.inventoryClicked(event);
        verify(pageSwitcher).switchPage(eq(uuid), any());
        reset(pageSwitcher);
    }

}
package me.riguron.bukkit.gui.multipage;

import org.junit.Before;
import org.junit.Test;
import me.riguron.bukkit.gui.Executable;
import me.riguron.bukkit.gui.item.InventoryItem;
import me.riguron.bukkit.gui.multipage.player.PlayerPages;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

public class SlotActionsTest {

    private SlotActions slotActions;
    private PlayerPages playerPages;

    @Before
    public void setUp() {
        this.playerPages = mock(PlayerPages.class);
        this.slotActions = new SlotActions(playerPages);
    }

    @Test
    public void executeSlotActions() {
        addAtSlotAndExecute(1, 10);
        addAtSlotAndExecute(2, 20);
        addAtSlotAndExecute(1, 0);
        addAtSlotAndExecute(2, 0);
        addAtSlotAndExecute(3, 54);
        addAtSlotAndExecute(2, 53);
    }

    @Test
    public void shiftAfterRemoval() {

        Executable oneZero = addAtSlot(1, 0);
        Executable oneTen = addAtSlot(1, 10);
        Executable one53 = addAtSlot(1, 53);

        Executable twoZero = addAtSlot(2, 0);
        Executable twoTwenty = addAtSlot(2, 20);
        Executable two53 = addAtSlot(2, 53);

        Executable threeZero = addAtSlot(3, 0);
        Executable threeForty = addAtSlot(3, 40);
        Executable three53 = addAtSlot(3, 53);

        slotActions.shiftAfterPageRemoval(2);

        executeAndAssertCalled(1, 0, oneZero, true);
        executeAndAssertCalled(1, 10, oneTen, true);
        executeAndAssertCalled(1, 53, one53, true);


        executeAndAssertCalled(2, 0, threeZero, true);
        executeAndAssertCalled(2, 40, threeForty, true);
        executeAndAssertCalled(2, 53, three53, true);

        // Verify that removedButtons are completely erased
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 55; j++) {
                executeAndAssertCalled(i, j, twoZero, false);
                executeAndAssertCalled(i, j, twoTwenty, false);
                executeAndAssertCalled(i, j, two53, false);
            }
        }
    }

    private void executeAndAssertCalled(int page, int slot, Executable action, boolean shouldCall) {
        execute(page, slot);
        verify(action, times(shouldCall ? 1 : 0)).execute(any());
    }

    private void execute(int page, int slot) {
        when(playerPages.getPlayerPage(any())).thenReturn(page);
        slotActions.executeSlotAction(UUID.randomUUID(), slot);
    }

    private Executable addAtSlot(int page, int slot) {
        InventoryItem inventoryItem = mock(InventoryItem.class);
        when(inventoryItem.getSlot()).thenReturn(slot);
        when(inventoryItem.getPage()).thenReturn(page);
        Executable executable = mock(Executable.class);
        slotActions.addSlotAction(inventoryItem, executable);
        return executable;
    }

    private void addAtSlotAndExecute(int page, int slot) {
        when(playerPages.getPlayerPage(any())).thenReturn(page);
        Executable executable = addAtSlot(page, slot);
        slotActions.executeSlotAction(UUID.randomUUID(), slot);
        verify(executable).execute(any());
    }


}
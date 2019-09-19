package io.riguron.bukkit.gui.multipage;

import org.bukkit.Server;
import org.bukkit.inventory.Inventory;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class InventoryPagesTest {

    private InventoryPages inventoryPages;
    private PageBuilder pageBuilder;
    private List<Inventory> inventories = new ArrayList<>();

    @Before
    public void setUp() {
        Server server = mock(Server.class);
        when(server.createInventory(any(), anyInt(), any())).thenAnswer(
                invocationOnMock -> {
                    Inventory inventory = mock(Inventory.class);
                    when(inventory.getTitle()).thenReturn(invocationOnMock.getArgument(2));
                    if (!inventory.getTitle().isEmpty()) {
                        inventories.add(inventory);
                    }
                    return inventory;
                }
        );
        pageBuilder = mock(PageBuilder.class);
        inventoryPages = new InventoryPages(new PagesList(), server, pageBuilder);
        when(pageBuilder.pageTitle(anyInt(), anyInt()))
                .thenAnswer(invocationOnMock ->
                        invocationOnMock.getArgument(0) + "/" + invocationOnMock.getArgument(1));

    }

    @Test
    public void whenAddOnePage() {
        inventoryPages.addNewPage();
        verify(pageBuilder, times(0)).rebuildPageByIndex(anyInt());
        verify(pageBuilder).addSwitchItems(eq(0), eq(inventories.get(0)));
        verify(pageBuilder, times(1)).rebuildPageSwitchers(eq(-1));
        assertEquals("1/1", inventories.get(0).getTitle());
    }

    @Test
    public void whenAddMultiplePages() {

        for (int i = 0; i < 4; i++) {
            inventoryPages.addNewPage();
            verify(pageBuilder).addSwitchItems(eq(i), eq(inventories.get(i)));
        }

        assertEquals(4, inventories.size());

        for (int i = 0; i < 4; i++) {
            // 1st page should be rebuild 3 times, 2d - 2 times and so on
            verify(pageBuilder, times(3 - i)).rebuildPageByIndex(eq(i));
        }

        for (int i = 0; i < 3; i++) {
            // Each consequent extreme page is rebuilt, except the last one
            verify(pageBuilder, times(1)).rebuildPageSwitchers(eq(i));
        }
    }

    @Test
    public void clearAll() {
        addFourPages();
        inventoryPages.clearAll();
        assertEquals(1, inventoryPages.getTotalPages());
        verify(inventories.get(0)).clear();
    }

    @Test
    public void getTotalPages() {
        addFourPages();
        assertEquals(4, inventoryPages.getTotalPages());
    }

    @Test
    public void getPage() {
        addFourPages();
        assertSame(inventories.get(1), inventoryPages.getPage(2));
        assertSame(inventories.get(3), inventoryPages.getPage(4));
    }


    @Test
    public void whenRemoveFirstPageThenRemoved() {
        inventoryPages.addNewPage();
        inventoryPages.addNewPage();

        reset(pageBuilder);

        inventoryPages.removePage(2);
        verify(pageBuilder).rebuildPageByIndex(eq(0));
        verify(pageBuilder).rebuildSwitchersAfterRemoval(eq(1));
        assertEquals(1, inventoryPages.getTotalPages());
    }

    @Test
    public void whenRemoveLastPageThenRemoved() {
        inventoryPages.addNewPage();
        inventoryPages.addNewPage();

        reset(pageBuilder);
        inventoryPages.removePage(1);
        verify(pageBuilder).rebuildPageByIndex(eq(0));
        verify(pageBuilder).rebuildSwitchersAfterRemoval(eq(0));
        assertEquals(1, inventoryPages.getTotalPages());
    }

    @Test
    public void whenRemovePageFromMiddleThenRemoved() {

        inventoryPages.addNewPage();
        inventoryPages.addNewPage();
        inventoryPages.addNewPage();

        reset(pageBuilder);
        inventoryPages.removePage(2);
        for (int i = 0; i < 2; i++) {
            verify(pageBuilder).rebuildPageByIndex(eq(i));
        }
        verify(pageBuilder).rebuildSwitchersAfterRemoval(eq(1));

    }


    private void addFourPages() {
        for (int i = 0; i < 4; i++) {
            inventoryPages.addNewPage();
        }
    }
}
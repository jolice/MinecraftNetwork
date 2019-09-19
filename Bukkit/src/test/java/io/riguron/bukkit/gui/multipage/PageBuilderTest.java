package io.riguron.bukkit.gui.multipage;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.legacy.PowerMockRunner;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static io.riguron.bukkit.gui.multipage.MultiPageButtons.NEXT_PAGE;
import static io.riguron.bukkit.gui.multipage.MultiPageButtons.PREVIOUS_PAGE;
import static io.riguron.bukkit.gui.multipage.MockItemFactory.mockItemFactory;


@RunWith(PowerMockRunner.class)
@PrepareForTest(Bukkit.class)
public class PageBuilderTest {

    @Before
    public void mockBukkit() {
        mockItemFactory();
    }

    @Test
    public void rebuildSwitchersAfterRemoval() {
        PagesList pagesList = mock(PagesList.class);
        PageBuilder pageBuilder = new PageBuilder(
                "Title",
                mock(Server.class),
                pagesList
        );

        afterRemovingFirstElement(pagesList, pageBuilder);
        afterRemovingMidElement(pagesList, pageBuilder);
        afterRemovingLastElement(pagesList, pageBuilder);

    }

    private void afterRemovingFirstElement(PagesList pagesList, PageBuilder pageBuilder) {
        Inventory inventory = mock(Inventory.class);
        when(pagesList.get(eq(0))).thenReturn(inventory);
        when(pagesList.size()).thenReturn(5);
        pageBuilder.rebuildSwitchersAfterRemoval(0);
        assertItemsSet(inventory, true, false);
    }

    private void afterRemovingMidElement(PagesList pagesList, PageBuilder pageBuilder) {
        Inventory inventory = mock(Inventory.class);
        when(pagesList.get(eq(2))).thenReturn(inventory);
        when(pagesList.size()).thenReturn(5);
        pageBuilder.rebuildSwitchersAfterRemoval(2);
        assertItemsSet(inventory, false, false);
    }


    private void afterRemovingLastElement(PagesList pagesList, PageBuilder pageBuilder) {
        Inventory inventory = mock(Inventory.class);
        when(pagesList.get(eq(2))).thenReturn(inventory);
        when(pagesList.size()).thenReturn(3);
        pageBuilder.rebuildSwitchersAfterRemoval(3);
        assertItemsSet(inventory, false, true);
    }

    private void assertItemsSet(Inventory inventory, boolean nextPage, boolean previousPage) {
        verify(inventory, times(nextPage ? 1 : 0))
                .setItem(eq(NEXT_PAGE.getSlot()), any());
        verify(inventory, times(previousPage ? 1 : 0))
                .setItem(eq(PREVIOUS_PAGE.getSlot()), any());
        reset(inventory);
    }

    @Test
    public void addSwitchItems() {

        PagesList pagesList = mock(PagesList.class);
        PageBuilder pageBuilder = new PageBuilder(
                "Title",
                mock(Server.class),
                pagesList
        );

        when(pagesList.size()).thenReturn(3);

        Inventory page = mock(Inventory.class);

        pageBuilder.addSwitchItems(0, page);
        assertItemsSet(page, true, false);

        pageBuilder.addSwitchItems(1, page);
        assertItemsSet(page, true, true);

        pageBuilder.addSwitchItems(2, page);
        assertItemsSet(page,  false, true);
    }

    @Test
    public void rebuildPageByIndex() {
        PagesList pagesList = mock(PagesList.class);
        Server server = mock(Server.class);
        PageBuilder pageBuilder = new PageBuilder(
                "Title",
               server,
                pagesList
        );

        Inventory src = mock(Inventory.class);

        when(src.getSize()).thenReturn(10);
        ItemStack content = new ItemStack(Material.AIR);
        when(src.getItem(eq(5))).thenReturn(content);

        Inventory newInv = mock(Inventory.class);
        when(server.createInventory(any(), anyInt(), any())).thenReturn(newInv);
        when(pagesList.get(0)).thenReturn(src);



        pageBuilder.rebuildPageByIndex(0);

        verify(pagesList).set(eq(0), eq(newInv));
        verify(newInv).setItem(eq(5), eq(content));

    }

}
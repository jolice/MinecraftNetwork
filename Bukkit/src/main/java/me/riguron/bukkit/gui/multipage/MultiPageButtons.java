package me.riguron.bukkit.gui.multipage;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.bukkit.Material;
import me.riguron.bukkit.gui.item.InventoryItem;
import me.riguron.bukkit.item.ItemStackBuilder;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class MultiPageButtons {

    static final InventoryItem NEXT_PAGE = new NextPage();
    static final InventoryItem PREVIOUS_PAGE = new PreviousPage();

    private static class NextPage extends InventoryItem {

        private static final int LAST_ITEM_SLOT = 53;

        private NextPage() {
            super(new ItemStackBuilder(Material.ARROW).displayName("Next page").build(), LAST_ITEM_SLOT );
        }
    }

    private static class PreviousPage extends InventoryItem {

        private static final int PREVIOUS_ITEM = 45;

        private PreviousPage() {
            super(new ItemStackBuilder(Material.ARROW).displayName("Previous page").build(), PREVIOUS_ITEM);
        }
    }

}

package com.github.jolice.gui.multipage;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.bukkit.Material;
import com.github.jolice.gui.item.InventoryItem;
import com.github.jolice.item.ItemStackBuilder;

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

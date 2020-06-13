package com.github.jolice.game.kit;

import com.github.jolice.game.kit.selector.inventory.KitSelectorInterface;
import lombok.RequiredArgsConstructor;
import com.github.jolice.item.executable.ExecutableItem;
import org.bukkit.entity.Player;

/**
 * Kit selector item listener.
 */
@RequiredArgsConstructor
public class KitSelectItem implements ExecutableItem {

    private final KitSelectorInterface selectorInterface;

    @Override
    public void execute(Player player) {
        selectorInterface.displayTo(player);
    }


}

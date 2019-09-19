package io.riguron.game.kit;

import io.riguron.game.kit.selector.inventory.KitSelectorInterface;
import lombok.RequiredArgsConstructor;
import io.riguron.bukkit.item.executable.ExecutableItem;
import io.riguron.game.kit.selector.inventory.KitSelectorInterface;
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

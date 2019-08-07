package me.riguron.game.kit;

import lombok.RequiredArgsConstructor;
import me.riguron.bukkit.item.executable.ExecutableItem;
import me.riguron.game.kit.selector.inventory.KitSelectorInterface;
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

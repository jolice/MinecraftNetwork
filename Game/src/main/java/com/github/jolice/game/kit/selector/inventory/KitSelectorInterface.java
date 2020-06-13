package com.github.jolice.game.kit.selector.inventory;

import com.github.jolice.game.kit.Kit;
import com.github.jolice.game.kit.Kits;
import com.github.jolice.game.kit.permissions.KitChallenger;
import com.github.jolice.game.kit.permissions.KitChallengerAdapter;
import com.github.jolice.game.kit.selector.KitSelector;
import lombok.RequiredArgsConstructor;
import com.github.jolice.gui.GUI;
import com.github.jolice.gui.item.InventoryItem;
import com.github.jolice.gui.single.SinglePageInterface;
import com.github.jolice.item.ItemStackBuilder;
import io.riguron.system.player.PlayerProfile;
import io.riguron.system.player.PlayerProfileRepository;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class KitSelectorInterface {

    private static final String TITLE = "Kit selector";

    private static final String NOT_AVAILABLE = "Not available: %s";

    private final Kits kits;
    private final PlayerProfileRepository repository;
    private final Plugin plugin;
    private final KitSelector kitSelector;

    public void displayTo(Player player) {
        GUI gui = new SinglePageInterface(plugin, TITLE);
        PlayerProfile profile = repository.get(player.getUniqueId());
        KitChallenger kitChallenger = new KitChallengerAdapter(profile);
        List<Kit> allKits = kits.getAllKits();
        for (int i = 0; i < allKits.size(); i++) {
            Kit kit = allKits.get(i);
            ItemStack icon = buildIcon(kit, kitChallenger);
            gui.addButton(new InventoryItem(icon, i), playerId -> kitSelector.select(kit, playerId));
        }
        gui.displayTo(player);
    }


    private ItemStack buildIcon(Kit kit, KitChallenger kitChallenger) {
        Material icon = kit.getKitInformation().getIcon();
        List<String> lore = new ArrayList<>(kit.getDescription());
        if (kit.getKitPermissions().isAvailableTo(kitChallenger)) {
            lore.add(String.format(NOT_AVAILABLE, kit.getKitPermissions().unavailabilityMessage(kitChallenger)));
        }
        return new ItemStackBuilder(icon)
                .displayName(kit.getKitInformation().getName())
                .lore(lore).build();
    }
}

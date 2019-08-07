package me.riguron.bukkit.selector.gui;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import me.riguron.bukkit.item.ItemStackBuilder;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FastServerItem {

    public static final ItemStack ICON = new ItemStackBuilder(Material.NETHER_STAR)
            .displayName("Instant join")
            .lore()
            .emptyLine()
            .addLine("> Click here to connect to the most available server")
            .emptyLine()
            .build();
}

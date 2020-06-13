package com.github.jolice.item;

import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Convenience builder for the ItemStack.
 */
public class ItemStackBuilder {

    private ItemStack itemStack;

    public ItemStackBuilder(Material material) {
        this.itemStack = new ItemStack(material);
    }

    /**
     * Sets item's display name.
     *
     * @param displayName name of the item. May contain color codes
     * @return current builder instance
     */
    public ItemStackBuilder displayName(String displayName) {
        return modifyItemMeta(itemMeta -> {
            if (displayName != null) {
                itemMeta.setDisplayName(displayName);
            }
        });
    }

    /**
     * Sets item's material data.
     *
     * @param materialDataSupplier supplier providing MaterialData object.
     * @return current builder instance
     */
    public ItemStackBuilder materialData(Supplier<MaterialData> materialDataSupplier) {
        return materialData(materialDataSupplier.get());
    }

    /**
     * Sets item's material data
     *
     * @param materialData native MaterialData object
     * @return current builder instance
     */
    public ItemStackBuilder materialData(MaterialData materialData) {
        if (materialData != null) {
            itemStack.setData(materialData);
        }
        return this;
    }

    /**
     * Sets item's lore.
     *
     * @param lore array of lore strings, each of them may contain color codes.
     * @return current builder instance
     */
    public ItemStackBuilder lore(String... lore) {
        return lore(Arrays.asList(lore));
    }

    /**
     * Sets item's lore.
     *
     * @param lore list of lore strings, each of them may contain color codes.
     * @return current builder instance
     */
    public ItemStackBuilder lore(List<String> lore) {
        return this.modifyItemMeta(itemMeta -> itemMeta.setLore(Collections.unmodifiableList(lore)));
    }

    /**
     * Enters lore construction mode.
     *
     * @return LoreBuilder object
     */
    public LoreBuilder lore() {
        return new LoreBuilder(this);
    }


    private ItemStackBuilder modifyItemMeta(Consumer<ItemMeta> itemMetaConsumer) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (!itemStack.hasItemMeta()) {
            // Static method is used here intentionally for the convenience.
            // Tests involving this class must mock this static method in order to pass.
            itemMeta = Bukkit.getItemFactory().getItemMeta(itemStack.getType());
            itemStack.setItemMeta(itemMeta);
        }
        itemMetaConsumer.accept(itemMeta);
        return this;
    }

    /**
     * Returns created item.
     *
     * @return constructed item stack.
     */
    public ItemStack build() {
        return itemStack;
    }

    /**
     * Class containing convenience methods for building
     * ItemStack lore.
     */
    @RequiredArgsConstructor
    public class LoreBuilder {

        private List<String> lore = new ArrayList<>();

        private final ItemStackBuilder owner;

        public LoreBuilder addLine(String line) {
            lore.add(line);
            return this;
        }

        public LoreBuilder emptyLine() {
            return addLine("");
        }

        ItemStackBuilder apply() {
            owner.lore(this.lore);
            return owner;
        }

        public ItemStack build() {
            return apply().build();
        }
    }
}

package io.riguron.bukkit.item;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.multibindings.ProvidesIntoSet;
import io.riguron.bukkit.item.entity.InventoryItemEntity;
import io.riguron.bukkit.item.entity.ItemStackEntity;
import io.riguron.bukkit.item.entity.MaterialDataEntity;
import io.riguron.bukkit.item.entity.WoolMaterialData;
import io.riguron.bukkit.item.entity.InventoryItemEntity;
import io.riguron.bukkit.item.entity.ItemStackEntity;
import io.riguron.bukkit.item.entity.MaterialDataEntity;
import io.riguron.bukkit.item.entity.WoolMaterialData;
import io.riguron.bukkit.item.executable.ExecutableItemListener;
import io.riguron.bukkit.item.executable.ExecutableItems;
import io.riguron.bukkit.item.join.JoinItem;
import io.riguron.bukkit.item.join.JoinItems;
import io.riguron.persistence.entity.EntityGroup;
import org.bukkit.event.Listener;

import java.util.Arrays;
import java.util.Set;

public class ItemModule extends AbstractModule {

    @Singleton
    @ProvidesIntoSet
    public EntityGroup defaultEntities() {
        return new EntityGroup(Arrays.asList(ItemStackEntity.class, InventoryItemEntity.class, MaterialDataEntity.class, WoolMaterialData.class));
    }

    @Singleton
    @ProvidesIntoSet
    public Listener executableItemListener(ExecutableItems items) {
        return new ExecutableItemListener(items);
    }

    @Singleton
    @Provides
    public ExecutableItems executableItems() {
        return new ExecutableItems();
    }

    @Singleton
    @Provides
    public JoinItems joinItems(Set<JoinItem> items, ExecutableItems executableItems) {
        return new JoinItems(items, executableItems);
    }

}

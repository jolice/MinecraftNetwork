package com.github.jolice.item;

import com.github.jolice.item.entity.InventoryItemEntity;
import com.github.jolice.item.entity.ItemStackEntity;
import com.github.jolice.item.entity.MaterialDataEntity;
import com.github.jolice.item.entity.WoolMaterialData;
import com.github.jolice.item.executable.ExecutableItemListener;
import com.github.jolice.item.executable.ExecutableItems;
import com.github.jolice.item.join.JoinItem;
import com.github.jolice.item.join.JoinItems;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.multibindings.ProvidesIntoSet;
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

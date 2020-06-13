package com.github.jolice.shop;

import com.github.jolice.shop.ui.ShopListener;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.multibindings.ProvidesIntoSet;
import io.ebean.EbeanServer;
import com.github.jolice.gui.GUIFactory;
import io.riguron.persistence.entity.EntityGroup;
import io.riguron.system.player.PlayerProfileRepository;
import io.riguron.system.shop.*;
import io.riguron.system.task.TaskFactory;
import org.bukkit.Server;
import org.bukkit.event.Listener;

import java.util.Arrays;

public class ShopModule extends AbstractModule {

    @Provides
    @Singleton
    public ActivePurchases activePurchases(PlayerPurchaseService playerPurchaseService, TaskFactory taskFactory) {
        return new ActivePurchases(playerPurchaseService, taskFactory);
    }

    @Provides
    @Singleton
    public PlayerPurchaseService playerPurchaseService(PlayerProfileRepository playerProfileRepository, PurchaseRepository purchaseRepository) {
        return new PlayerPurchaseService(playerProfileRepository);
    }

    @Provides
    @Singleton
    public Shop shop(ActivePurchases activePurchases, ShopViews shopViews, Server server) {
        return new Shop(shopViews, activePurchases, server);
    }

    @ProvidesIntoSet
    @Singleton
    public Listener shopInventoryListener(ActivePurchases activePurchases, ShopViews shopViews) {
        return new ShopListener(activePurchases, shopViews);
    }

    @Provides
    @Singleton
    public ShopViews shopViews(GUIFactory guiFactory, ShopInventoryButtons shopInventoryButtons) {
        return new ShopViews(guiFactory, shopInventoryButtons);
    }

    @Singleton
    @Provides
    public ShopInventoryButtons shopInventoryButtons(Server server, ActivePurchases activePurchases) {
        return new ShopInventoryButtons(server, activePurchases);
    }

    @Provides
    @Singleton
    public PurchaseRepository purchaseRepository(EbeanServer ebeanServer) {
        return new PurchaseRepository(ebeanServer);
    }

    @ProvidesIntoSet
    @Singleton
    public EntityGroup entityGroup() {
        return new EntityGroup(Arrays.asList(Purchase.class, PlayerPurchases.class));
    }


}

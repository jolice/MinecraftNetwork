package io.riguron.game.kit;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.multibindings.ProvidesIntoSet;
import io.ebean.EbeanServer;
import io.riguron.game.kit.permissions.AnyoneKitPermissions;
import io.riguron.game.kit.permissions.BalanceKitPermissions;
import io.riguron.game.kit.permissions.KitPermissions;
import io.riguron.game.kit.permissions.RankBasedPermissions;
import io.riguron.game.kit.selector.KitSelector;
import io.riguron.game.kit.selector.inventory.KitSelectorInterface;
import io.riguron.game.player.repository.GamePlayerStorage;
import io.riguron.bukkit.gui.item.InventoryItem;
import io.riguron.bukkit.item.ItemStackBuilder;
import io.riguron.bukkit.item.join.JoinItem;
import io.riguron.bukkit.shop.Shop;
import io.riguron.game.config.GameOptions;
import io.riguron.game.kit.permissions.AnyoneKitPermissions;
import io.riguron.game.kit.permissions.BalanceKitPermissions;
import io.riguron.game.kit.permissions.KitPermissions;
import io.riguron.game.kit.permissions.RankBasedPermissions;
import io.riguron.game.kit.selector.KitSelector;
import io.riguron.game.kit.selector.inventory.KitSelectorInterface;
import io.riguron.game.player.repository.GamePlayerStorage;
import io.riguron.persistence.entity.EntityGroup;
import io.riguron.system.player.PlayerProfileRepository;
import io.riguron.system.task.startup.PostLoadTask;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;

public class KitModule extends AbstractModule {

    @ProvidesIntoSet
    @Singleton
    public EntityGroup entityGroup() {
        return new EntityGroup(Arrays.asList(AnyoneKitPermissions.class, BalanceKitPermissions.class, KitPermissions.class, RankBasedPermissions.class, Kit.class, KitInformation.class));
    }

    @ProvidesIntoSet
    @Singleton
    public Listener kitPlayerSupplier(GamePlayerStorage<?> storage, Server server, Kits kits) {
        return new KitPlayerSupplier(storage, server, kits);
    }

    @Provides
    @Singleton
    public KitSelector kitSelector(GamePlayerStorage<?> storage, Shop shop, PlayerProfileRepository repo) {
        return new KitSelector(storage, shop, repo);
    }

    @ProvidesIntoSet
    @Singleton
    public PostLoadTask kitLoader(Kits kits, KitRepository kitRepository, GameOptions gameOptions) {
        return new KitLoader(kits, kitRepository, gameOptions);
    }

    @Provides
    @Singleton
    public KitRepository kitRepository(EbeanServer ebeanServer) {
        return new KitRepository(ebeanServer);
    }

    @Provides
    @Singleton
    public Kits kits() {
        return new Kits();
    }

    @Provides
    @Singleton
    public KitSelectorInterface kitSelectorInterface(Kits kits, PlayerProfileRepository repository, Plugin plugin, KitSelector kitSelector) {
        return new KitSelectorInterface(kits, repository, plugin, kitSelector);
    }

    @ProvidesIntoSet
    @Singleton
    public JoinItem kitSelectItem(KitSelectorInterface kitSelectorInterface) {
        return new JoinItem(new InventoryItem(new ItemStackBuilder(Material.ARROW)
                .displayName("Kit Selector")
                .lore("Use this item to select kit")
                .build(), 2),
                new KitSelectItem(kitSelectorInterface));
    }

}

package me.riguron.game.kit;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.multibindings.ProvidesIntoSet;
import io.ebean.EbeanServer;
import me.riguron.bukkit.gui.item.InventoryItem;
import me.riguron.bukkit.item.ItemStackBuilder;
import me.riguron.bukkit.item.join.JoinItem;
import me.riguron.bukkit.shop.Shop;
import me.riguron.game.config.GameOptions;
import me.riguron.game.kit.permissions.AnyoneKitPermissions;
import me.riguron.game.kit.permissions.BalanceKitPermissions;
import me.riguron.game.kit.permissions.KitPermissions;
import me.riguron.game.kit.permissions.RankBasedPermissions;
import me.riguron.game.kit.selector.KitSelector;
import me.riguron.game.kit.selector.inventory.KitSelectorInterface;
import me.riguron.game.player.repository.GamePlayerStorage;
import me.riguron.persistence.entity.EntityGroup;
import me.riguron.system.player.PlayerProfileRepository;
import me.riguron.system.task.startup.PostLoadTask;
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

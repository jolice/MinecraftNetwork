package me.riguron.game.kit.selector;

import lombok.RequiredArgsConstructor;
import me.riguron.bukkit.shop.Shop;
import me.riguron.game.kit.Kit;
import me.riguron.game.kit.permissions.KitChallengerAdapter;
import me.riguron.game.player.repository.GamePlayerStorage;
import me.riguron.game.shop.KitPurchasableAdapter;
import me.riguron.system.player.PlayerProfileRepository;

import java.util.UUID;

@RequiredArgsConstructor
public class KitSelector {

    private final GamePlayerStorage<?> storage;
    private final Shop shop;
    private final PlayerProfileRepository playerProfileRepository;

    public void select(Kit kit, UUID uuid) {
        if (kit.getKitPermissions().isAvailableTo(new KitChallengerAdapter(playerProfileRepository.get(uuid)))) {
            storage.getPlayer(uuid).getKit().setCurrentKit(kit);
        } else {
            if (kit.isPurchasable()) {
                shop.purchase(uuid, new KitPurchasableAdapter(kit), () -> storage.getPlayer(uuid).getKit().setCurrentKit(kit));
            }
        }
    }
}

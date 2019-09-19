package io.riguron.game.kit.selector;

import io.riguron.game.player.repository.GamePlayerStorage;
import lombok.RequiredArgsConstructor;
import io.riguron.bukkit.shop.Shop;
import io.riguron.game.kit.Kit;
import io.riguron.game.kit.permissions.KitChallengerAdapter;
import io.riguron.game.player.repository.GamePlayerStorage;
import io.riguron.game.shop.KitPurchasableAdapter;
import io.riguron.system.player.PlayerProfileRepository;

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

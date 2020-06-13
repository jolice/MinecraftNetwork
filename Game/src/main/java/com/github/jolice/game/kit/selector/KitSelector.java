package com.github.jolice.game.kit.selector;

import com.github.jolice.game.player.repository.GamePlayerStorage;
import lombok.RequiredArgsConstructor;
import com.github.jolice.shop.Shop;
import com.github.jolice.game.kit.Kit;
import com.github.jolice.game.kit.permissions.KitChallengerAdapter;
import com.github.jolice.game.shop.KitPurchasableAdapter;
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

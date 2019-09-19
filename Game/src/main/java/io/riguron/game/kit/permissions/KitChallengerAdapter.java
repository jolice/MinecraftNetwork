package io.riguron.game.kit.permissions;

import lombok.RequiredArgsConstructor;
import io.riguron.system.player.PlayerProfile;

@RequiredArgsConstructor
public class KitChallengerAdapter implements KitChallenger {

    private final PlayerProfile playerProfile;

    @Override
    public boolean hasPermission(String permission) {
        return playerProfile.hasPermission(permission);
    }

    @Override
    public long getCoins() {
        return playerProfile.getCoins();
    }

    @Override
    public boolean hasPurchase(int purchaseId) {
        return playerProfile.getPurchases().hasPurchase(purchaseId);
    }
}

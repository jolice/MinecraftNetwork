package io.riguron.system.shop;

import io.ebean.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import io.riguron.system.player.PlayerProfile;
import io.riguron.system.player.PlayerProfileRepository;

import java.util.UUID;

@RequiredArgsConstructor
public class PlayerPurchaseService {

    private final PlayerProfileRepository playerRepository;

    @Transactional
    public void purchase(UUID purchaser, Purchasable purchasable) {
        PlayerProfile playerProfile = playerRepository.get(purchaser);

        if (playerProfile.getPurchases().hasPurchase(purchasable.getId())) {
            throw new AlreadyPurchasedException();
        }

        playerProfile.withdraw(purchasable.getPrice());
        playerProfile.getPurchases().addPurchase(purchasable.getId(), new Purchase(purchasable.getPrice(), purchasable.getId(), purchasable.getDescription(), playerProfile));
        playerRepository.update(playerProfile);

    }
}

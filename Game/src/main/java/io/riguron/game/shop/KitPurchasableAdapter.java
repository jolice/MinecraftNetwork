package io.riguron.game.shop;

import io.riguron.game.kit.Kit;
import lombok.RequiredArgsConstructor;
import io.riguron.system.shop.Purchasable;
import io.riguron.game.kit.Kit;

@RequiredArgsConstructor
public class KitPurchasableAdapter implements Purchasable {

    private final Kit kit;

    @Override
    public int getId() {
        return kit.getId();
    }

    @Override
    public long getPrice() {
        return kit.getKitInformation().getPrice();
    }

    @Override
    public String getDescription() {
        return "Kit [" +  kit.getKitInformation().getName() + "]";
    }


}

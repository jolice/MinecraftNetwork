package me.riguron.system.shop;

import io.ebean.EbeanServer;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PurchaseRepository {

    private final EbeanServer ebeanServer;

    public void update(PlayerPurchases purchases) {
        ebeanServer.update(purchases);
    }

}

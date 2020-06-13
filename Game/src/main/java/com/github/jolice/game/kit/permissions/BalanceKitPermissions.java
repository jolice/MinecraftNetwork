package com.github.jolice.game.kit.permissions;

import lombok.EqualsAndHashCode;
import io.riguron.system.internalization.Message;
import io.riguron.system.shop.Purchasable;

import javax.persistence.Entity;

@Entity
@EqualsAndHashCode(callSuper = true)
public class BalanceKitPermissions extends KitPermissions {

    private Long price;
    private Integer purchasableId;

    public BalanceKitPermissions(Purchasable purchasable) {
        this.price = purchasable.getPrice();
        this.purchasableId = purchasable.getId();
    }

    @Override
    public boolean isAvailableTo(KitChallenger kitChallenger) {
        return kitChallenger.hasPurchase(purchasableId);
    }

    @Override
    public Message unavailabilityMessage(KitChallenger kitChallenger) {
        return new Message("game.kit.permission.balance", price, kitChallenger.getCoins());
    }

}

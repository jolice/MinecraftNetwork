package io.riguron.game.kit.permissions;

public interface KitChallenger {

    boolean hasPermission(String permission);

    long getCoins();

    boolean hasPurchase(int purchaseId);
}

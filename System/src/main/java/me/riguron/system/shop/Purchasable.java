package me.riguron.system.shop;

/**
 * Represents any server item that can be purchased.
 */
public interface Purchasable {

    /**
     * Internal ID of the purchase. Must be constant and unique.
     *
     * @return id of the purchase
     */
    int getId();

    /**
     * Service description of the purchase that is displayed to players.
     *
     * @return purchase description
     */
    String getDescription();

    long getPrice();


}

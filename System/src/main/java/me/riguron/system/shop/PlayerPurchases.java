package me.riguron.system.shop;

import lombok.Getter;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Stores purchases made by a specific player.
 */
@Entity
public class PlayerPurchases {

    @Id
    @Getter
    private UUID id;

    public PlayerPurchases(UUID id) {
        this.id = id;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable
    @MapKey(name = "purchaseId")
    private Map<Integer, Purchase> data = new HashMap<>();

    public boolean hasPurchase(Integer purchaseId) {
        return data.containsKey(purchaseId);
    }

    public void addPurchase(Integer id, Purchase purchase) {
        data.put(id, purchase);
    }


}

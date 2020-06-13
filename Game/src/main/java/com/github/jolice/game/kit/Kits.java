package com.github.jolice.game.kit;

import java.util.*;

/**
 * Simple in-memory kit storage.
 */
public class Kits {

    private Map<String, Kit> kitData = new HashMap<>();
    private Kit defaultKit;

    public void addKit(Kit kit) {
        kitData.put(kit.getKitInformation().getName(), kit);
        if (kit.getKitInformation().isDefaultKit()) {
            this.defaultKit = kit;
        }
    }

    public List<Kit> getAllKits() {
        return new ArrayList<>(kitData.values());
    }

    public Kit getDefaultKit() {
        return defaultKit;
    }


}

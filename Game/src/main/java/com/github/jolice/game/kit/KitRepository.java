package com.github.jolice.game.kit;

import io.ebean.EbeanServer;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class KitRepository {

    private final EbeanServer ebeanServer;

    public List<Kit> findKitsByGameName(String gameName) {
        return ebeanServer.createQuery(Kit.class)
                .fetch("description")
                .fetch("kitPermissions")
                .fetch("items")
                .where().eq("kitInformation.gameName", gameName)
                .findList();
    }

    public void save(Kit kit) {
        ebeanServer.save(kit);
    }
}


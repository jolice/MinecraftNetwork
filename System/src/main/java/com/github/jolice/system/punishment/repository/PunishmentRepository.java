package com.github.jolice.system.punishment.repository;

import io.ebean.EbeanServer;
import com.github.jolice.system.punishment.model.PunishmentRecord;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class PunishmentRepository {

    private final EbeanServer ebeanServer;

    public void savePunishment(PunishmentRecord punishmentRecord) {
        ebeanServer.save(punishmentRecord);
    }

    public List<PunishmentRecord> getPunishments(UUID userId, int page, int count) {
        return ebeanServer.createQuery(PunishmentRecord.class)
                .where().eq("target.uuid", userId)
                .setFirstRow(page * count)
                .setMaxRows(count)
                .findList();

    }

}

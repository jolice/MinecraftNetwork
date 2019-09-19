package io.riguron.system.dialog.ignore;

import io.ebean.DuplicateKeyException;
import io.ebean.EbeanServer;
import io.ebean.Query;
import lombok.RequiredArgsConstructor;
import io.riguron.system.player.PlayerProfile;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class IgnoreRepository {

    private final EbeanServer ebeanServer;

    public List<IgnoreHistoryElement> getIgnoreHistory(UUID profile, int page, int max) {
        return ebeanServer.findDto(IgnoreHistoryElement.class,
                "select p.name, i.date " +
                        "from ignore_data i " +
                        "inner join players p " +
                        "on i.target_id = p.id " +
                        "where i.user_id = ?")
                .setFirstRow(page * max)
                .setMaxRows(max)
                .setParameter(1, profile)
                .findList();
    }

    public boolean isIgnoring(UUID profile, String name) {
        return query(profile, name).exists();
    }

    public boolean ignore(UUID profile, PlayerProfile target) {
        try {
            ebeanServer.save(new IgnoreData(profile, target));
            return true;
        } catch (DuplicateKeyException e) {
            return false;
        }
    }

    public boolean removeIgnore(UUID profile, String name) {
        return query(profile, name).delete() > 0;
    }

    private Query<IgnoreData> query(UUID profile, String name) {
        return ebeanServer
                .find(IgnoreData.class)
                .where()
                .eq("userId", profile)
                .and()
                .eq("target.name", name)
                .query();
    }
}

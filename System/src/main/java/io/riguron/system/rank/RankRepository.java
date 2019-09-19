package io.riguron.system.rank;

import io.ebean.EbeanServer;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class RankRepository {

    private final EbeanServer ebeanServer;

    public void save(Rank rank) {
        ebeanServer.save(rank);
    }

    public List<Rank> findAll() {
        return ebeanServer.find(Rank.class)
                .fetch("permissions")
                .fetch("child")
                .findList();
    }

}

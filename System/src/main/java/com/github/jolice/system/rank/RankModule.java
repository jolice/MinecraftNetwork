package com.github.jolice.system.rank;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.multibindings.ProvidesIntoSet;
import io.ebean.EbeanServer;
import com.github.jolice.system.task.startup.PostLoadTask;
import com.github.jolice.persistence.entity.EntityGroup;

import java.util.Collections;

public class RankModule extends AbstractModule {

    @ProvidesIntoSet
    @Singleton
    public PostLoadTask rankLoader(Ranks ranks) {
        return new RankLoader(ranks);
    }

    @Provides
    @Singleton
    public RankRepository rankRepository(EbeanServer ebeanServer) {
        return new RankRepository(ebeanServer);
    }

    @ProvidesIntoSet
    @Singleton
    public EntityGroup entityGroup() {
        return new EntityGroup(Collections.singletonList(Rank.class));
    }

    @Provides
    @Singleton
    public Ranks ranks(RankRepository rankRepository) {
        return new Ranks(1, rankRepository);
    }


}

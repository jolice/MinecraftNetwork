package com.github.jolice.system.rank;

import com.github.jolice.system.task.startup.PostLoadTask;

/**
 * Post-load task responsible for loading Ranks into the memory
 */
public class RankLoader implements PostLoadTask {

    private final Ranks ranks;

    public RankLoader(Ranks ranks) {
        this.ranks = ranks;
    }

    @Override
    public void run() {
        ranks.loadRanks();
    }
}

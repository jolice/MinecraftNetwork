package com.github.jolice.system.rank;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Slf4j
public class Ranks {

    private final Map<Integer, Rank> allRanks = new HashMap<>();
    private final Integer defaultRankId;

    private final RankRepository rankRepository;

    public Rank getDefaultRank() {
        return allRanks.get(defaultRankId);
    }

    public Rank getRankSingleton(Integer rankId) {
        return allRanks.get(rankId);
    }

    public void loadRanks() {
        rankRepository.findAll().forEach(rank -> allRanks.put(rank.getId(), rank));
        log.info("Loaded {} ranks", allRanks.size());
    }

}

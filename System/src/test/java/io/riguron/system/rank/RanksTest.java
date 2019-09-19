package io.riguron.system.rank;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RanksTest {

    @Test
    public void getDefaultRank() {
        RankRepository rankRepository = mock(RankRepository.class);
        Ranks ranks = new Ranks(1, rankRepository);

        Rank expectedDefault = createRank(1);
        Rank second = createRank(2);
        Rank third = createRank(3);

        when(rankRepository.findAll()).thenReturn(
                Arrays.asList(expectedDefault,
                        second,
                        third
                )
        );

        ranks.loadRanks();

        assertEquals(expectedDefault, ranks.getDefaultRank());
        assertEquals(second, ranks.getRankSingleton(2));

    }

    private Rank createRank(int id) {
        Rank rank = mock(Rank.class);
        when(rank.getId()).thenReturn(id);
        return rank;
    }
}
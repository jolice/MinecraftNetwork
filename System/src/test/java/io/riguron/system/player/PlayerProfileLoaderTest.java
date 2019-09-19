package io.riguron.system.player;

import io.riguron.system.login.LoginDetails;
import io.riguron.system.rank.Rank;
import io.riguron.system.rank.Ranks;
import org.junit.Test;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PlayerProfileLoaderTest {

    @Test
    public void whenProfileDoesNotExists() {

        PlayerProfileRepository repository = mock(PlayerProfileRepository.class);
        Ranks ranks = mock(Ranks.class);
        PlayerProfileLoader loader = new PlayerProfileLoader(repository, ranks);
        PlayerProfile profile = mock(PlayerProfile.class);

        Rank rank = mock(Rank.class);
        when(rank.getId()).thenReturn(1);

        when(ranks.getRankSingleton(eq(1))).thenReturn(rank);

        when(profile.getName()).thenReturn("name");
        when(profile.getRank()).thenReturn(rank);
        when(repository.find(any(), any())).thenReturn(Optional.of(profile));
        LoginDetails loginDetails = new LoginDetails(profile.getUuid(), "name");
        loader.proceed(loginDetails);

        verify(profile).setRank(eq(rank));
        verify(repository).put(eq(profile.getUuid()), eq(profile));
    }

}
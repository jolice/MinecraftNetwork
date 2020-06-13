package com.github.jolice.system.dialog.ignore;

import com.github.jolice.system.player.PlayerProfile;
import com.github.jolice.system.player.PlayerProfileRepository;
import org.junit.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IgnoreServiceTest {

    @Test
    public void ignore() {
        PlayerProfileRepository playerRepository = mock(PlayerProfileRepository.class);
        IgnoreRepository ignoreRepository = mock(IgnoreRepository.class);
        IgnoreService ignoreService = new IgnoreService(playerRepository, ignoreRepository);

        PlayerProfile profile = mock(PlayerProfile.class);
        UUID src = UUID.randomUUID();
        when(playerRepository.find(any(), any())).thenReturn(Optional.of(profile));
        when(ignoreRepository.ignore(eq(src), eq(profile))).thenReturn(
                true
        );

        assertEquals(IgnoreResult.IGNORED,
                ignoreService.ignore(src, "target"));
    }

    @Test
    public void removeIgnore() {

        PlayerProfileRepository playerProfileRepository = mock(PlayerProfileRepository.class);

        UUID who = UUID.randomUUID();
        IgnoreRepository ignoreRepository = mock(IgnoreRepository.class);
        when(ignoreRepository.removeIgnore(eq(who), eq("target"))).thenReturn(
                true
        );

        IgnoreService ignoreService = new IgnoreService(playerProfileRepository, ignoreRepository);
        assertEquals(IgnoreRemoveResult.REMOVED_IGNORE,
                ignoreService.removeIgnore(who, "target")
        );
    }

    @Test
    public void isIgnoring() {
        PlayerProfileRepository playerProfileRepository = mock(PlayerProfileRepository.class);
        IgnoreRepository ignoreRepository = mock(IgnoreRepository.class);
        IgnoreService ignoreService = new IgnoreService(playerProfileRepository, ignoreRepository);

        UUID who = UUID.randomUUID();

        when(ignoreRepository.isIgnoring(eq(who), eq("target"))).thenReturn(true);
        assertTrue(ignoreService.isIgnoring(who, "target"));
    }
}
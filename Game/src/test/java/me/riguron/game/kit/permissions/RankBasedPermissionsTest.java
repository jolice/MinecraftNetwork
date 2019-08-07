package me.riguron.game.kit.permissions;

import me.riguron.system.rank.Rank;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RankBasedPermissionsTest {

    @Test
    public void whenHasRankThenAllowed() {
        Rank needed = mock(Rank.class);
        KitPermissions kitPermissions = new RankBasedPermissions(
                "permission", needed
        );

        KitChallenger kitChallenger = mock(KitChallenger.class);
        when(kitChallenger.hasPermission(eq("permission")))
                .thenReturn(true);

        assertTrue(
                kitPermissions.isAvailableTo(kitChallenger));
    }
}
package io.riguron.game.kit.permissions;

import io.riguron.system.shop.Purchasable;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BalanceKitPermissionsTest {

    @Test
    public void testAvailability() {
        int id = 10;
        Purchasable purchasable = mock(Purchasable.class);
        when(purchasable.getPrice()).thenReturn(100L);
        when(purchasable.getId()).thenReturn(id);


        BalanceKitPermissions kitPermissions = new BalanceKitPermissions(
                purchasable
        );

        KitChallenger kitChallenger = mock(KitChallenger.class);
        when(kitChallenger.hasPurchase(eq(id))).thenReturn(true);


        kitPermissions.isAvailableTo(kitChallenger);
    }

}
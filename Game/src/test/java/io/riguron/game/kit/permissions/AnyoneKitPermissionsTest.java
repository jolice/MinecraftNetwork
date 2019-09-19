package io.riguron.game.kit.permissions;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class AnyoneKitPermissionsTest {

    @Test
    public void isAvailableTo() {
        AnyoneKitPermissions anyoneKitPermissions = new AnyoneKitPermissions();
        assertTrue(anyoneKitPermissions.isAvailableTo(mock(KitChallenger.class)));
    }

    @Test(expected = KitAvailabilityException.class)
    public void unavailabilityMessage() {
        AnyoneKitPermissions anyoneKitPermissions = new AnyoneKitPermissions();
        anyoneKitPermissions.unavailabilityMessage(mock(KitChallenger.class));
    }
}
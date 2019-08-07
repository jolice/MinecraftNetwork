package me.riguron.bukkit.gui.multipage;

import org.junit.Test;
import me.riguron.bukkit.gui.multipage.player.PlayerPages;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.Assert.*;

public class PlayerPagesTest {

    @Test
    public void hasSession() {
        PlayerPages playerPages = new PlayerPages();
        UUID first = UUID.randomUUID();
        UUID second = UUID.randomUUID();
        playerPages.setPlayerPage(first, 1);
        assertTrue(playerPages.hasSession(first));
        assertFalse(playerPages.hasSession(second));
    }

    @Test
    public void clear() {
        PlayerPages playerPages = new PlayerPages();
        UUID first = UUID.randomUUID();
        playerPages.setPlayerPage(first, 1);
        playerPages.clear();
        assertTrue(playerPages.getActiveSessions().isEmpty());
        assertNull(playerPages.getPlayerPage(first));
    }

    @Test
    public void getActiveSessions() {
        PlayerPages playerPages = new PlayerPages();
        UUID first, second;
        do {
            first = UUID.randomUUID();
            second = UUID.randomUUID();

        } while (first.equals(second));

        playerPages.setPlayerPage(first, 2);
        playerPages.setPlayerPage(second, 3);
        assertEquals(new HashSet<>(Arrays.asList(first, second)), playerPages.getActiveSessions());
    }

    @Test
    public void rearrangeAfterRemoval() {
        PlayerPages playerPages = new PlayerPages();

        UUID a = UUID.randomUUID();
        UUID b = UUID.randomUUID();
        UUID c = UUID.randomUUID();
        UUID d = UUID.randomUUID();

        playerPages.setPlayerPage(a, 1);
        playerPages.setPlayerPage(b, 2);
        playerPages.setPlayerPage(c, 2);
        playerPages.setPlayerPage(d, 3);

        Set<UUID> currentlyLookingAtPage = playerPages.rearrangeAfterRemoval(2);
        assertEquals(new HashSet<>(Arrays.asList(b, c)), currentlyLookingAtPage);
        assertEquals(2, playerPages.getPlayerPage(d));
        assertEquals(1, playerPages.getPlayerPage(a));
        assertNull(playerPages.getPlayerPage(b));
        assertNull(playerPages.getPlayerPage(c));
    }

    @Test
    public void switchPage() {
        PlayerPages playerPages = new PlayerPages();
        UUID uuid = UUID.randomUUID();
        playerPages.setPlayerPage(uuid, 2);
        playerPages.switchPage(uuid, operand -> operand + 1);
        assertEquals(3, playerPages.getPlayerPage(uuid));
        playerPages.switchPage(uuid, operand -> operand - 1);
        assertEquals(2, playerPages.getPlayerPage(uuid));
    }

    @Test
    public void removePlayer() {
        PlayerPages playerPages = new PlayerPages();
        UUID uuid = UUID.randomUUID();
        playerPages.setPlayerPage(uuid, 1);
        assertEquals(1, playerPages.getPlayerPage(uuid));
        playerPages.removePlayer(uuid);
        assertNull(playerPages.getPlayerPage(uuid));
    }
}
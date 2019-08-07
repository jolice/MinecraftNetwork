package me.riguron.game.player.spectator;

import lombok.RequiredArgsConstructor;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

/**
 * Utility class for spectators.
 */
@RequiredArgsConstructor
public class Spectator {

    private final Plugin plugin;
    private final Server server;

    public void set(Player player, boolean spectator) {
        if (spectator) {
            player.setAllowFlight(true);
            player.setFlying(true);
            player.setFireTicks(0);
            player.setVelocity(new Vector(0, 0, 0));
            player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 100000000, 0));
        } else {
            player.removePotionEffect(PotionEffectType.INVISIBILITY);
        }
        this.changeVisibility(player, spectator);
    }

    private void changeVisibility(Player player, boolean hide) {
        for (Player p : server.getOnlinePlayers()) {
            if (p != player) {
                if (hide) {
                    p.hidePlayer(plugin, player);
                } else {
                    p.showPlayer(plugin, player);
                }
            }
        }
    }
}

package io.riguron.game.kit;

import io.riguron.game.event.GameStartEvent;
import io.riguron.game.player.GamePlayer;
import io.riguron.game.player.repository.GamePlayerStorage;
import lombok.RequiredArgsConstructor;
import io.riguron.game.event.GameStartEvent;
import io.riguron.game.player.GamePlayer;
import io.riguron.game.player.repository.GamePlayerStorage;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Class responsible for giving kit's items to the players.
 */
@RequiredArgsConstructor
public class KitPlayerSupplier implements Listener {

    private final GamePlayerStorage<?> storage;
    private final Server server;
    private final Kits kits;

    @EventHandler
    public void onGameStart(GameStartEvent event) {
        storage.getAllPlayers().forEach(gamePlayer -> {
            Player player = server.getPlayer(gamePlayer.getId());
            player.getInventory().clear();
            this.getPlayerKit(gamePlayer).getItems()
                    .forEach(item -> player.getInventory().setItem(item.getPosition(),
                            item.getItem().toItemStack()));
        });
    }

    private Kit getPlayerKit(GamePlayer gamePlayer) {
        Kit currentKit = gamePlayer.getKit().getCurrentKit();
        return currentKit != null ? currentKit : kits.getDefaultKit();
    }
}

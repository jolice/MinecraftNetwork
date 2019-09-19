package io.riguron.bukkit.selector.item;

import io.riguron.bukkit.selector.ServerItemProvider;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import io.riguron.bukkit.item.ItemStackBuilder;
import io.riguron.bukkit.selector.ServerItemProvider;
import io.riguron.server.type.GameServer;

public class GameServerItemProvider implements ServerItemProvider<ItemStack, GameServer> {

    @Override
    public ItemStack createServerItem(GameServer gameServer) {
        return new ItemStackBuilder(Material.EMERALD_BLOCK)
                .displayName(gameServer.getName())
                .lore()
                .emptyLine()
                .addLine(String.format("Game: %s", gameServer.getGameName()))
                .addLine(String.format("Map: %s", gameServer.getMap()))
                .addLine(String.format("Players: %d /%d", gameServer.getOnlinePlayers(), gameServer.getMaxPlayers()))
                .build();
    }
}

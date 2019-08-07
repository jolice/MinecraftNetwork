package me.riguron.bukkit.selector.item;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import me.riguron.bukkit.item.ItemStackBuilder;
import me.riguron.bukkit.selector.ServerItemProvider;
import me.riguron.server.type.GameServer;

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

package me.riguron.bukkit.selector.global;

import lombok.RequiredArgsConstructor;
import org.bukkit.Server;
import me.riguron.bukkit.gui.Executable;
import me.riguron.bukkit.selector.SelectorRepository;

@RequiredArgsConstructor
public class GlobalSelectorItemFactory {

    private final SelectorRepository selectorRepository;
    private final Server server;

    public Executable newGlobalSelectorButton(String serverChannel) {
        return playerId -> selectorRepository.getSelector(serverChannel).displayTo(
                server.getPlayer(playerId)
        );
    }

}

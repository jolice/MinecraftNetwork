package io.riguron.bukkit.selector.global;

import io.riguron.bukkit.selector.SelectorRepository;
import lombok.RequiredArgsConstructor;
import org.bukkit.Server;
import io.riguron.bukkit.gui.Executable;
import io.riguron.bukkit.selector.SelectorRepository;

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

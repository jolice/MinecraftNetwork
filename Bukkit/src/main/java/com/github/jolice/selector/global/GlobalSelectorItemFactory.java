package com.github.jolice.selector.global;

import com.github.jolice.selector.SelectorRepository;
import lombok.RequiredArgsConstructor;
import org.bukkit.Server;
import com.github.jolice.gui.Executable;

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

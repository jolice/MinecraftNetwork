package com.github.jolice.game.listener.start;

import com.github.jolice.game.team.client.TeamAssigner;
import lombok.RequiredArgsConstructor;
import com.github.jolice.game.event.GameStartEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

@RequiredArgsConstructor
public class TeamGameStartListener implements Listener {

    private final TeamAssigner teamAssigner;

    @EventHandler
    public void onGameStart(GameStartEvent e) {
        teamAssigner.assignRemaining();
    }


}

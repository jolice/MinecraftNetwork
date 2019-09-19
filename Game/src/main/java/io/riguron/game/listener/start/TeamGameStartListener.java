package io.riguron.game.listener.start;

import io.riguron.game.team.client.TeamAssigner;
import lombok.RequiredArgsConstructor;
import io.riguron.game.event.GameStartEvent;
import io.riguron.game.team.client.TeamAssigner;
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

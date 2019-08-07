package me.riguron.game.listener.start;

import lombok.RequiredArgsConstructor;
import me.riguron.game.event.GameStartEvent;
import me.riguron.game.team.client.TeamAssigner;
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

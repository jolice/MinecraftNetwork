package io.riguron.game.team.selector;

import lombok.RequiredArgsConstructor;
import io.riguron.game.listener.state.waiting.WaitingStateListener;
import io.riguron.game.player.repository.GamePlayerStorage;
import io.riguron.game.player.team.TeamPlayer;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

@RequiredArgsConstructor
public class TeamGuiListener extends WaitingStateListener {

    private final GamePlayerStorage<TeamPlayer> gamePlayerStorage;
    private final TeamSelectorInventory teamSelectorInventory;

    @EventHandler
    public void onItemClick(InventoryClickEvent e) {
        if (teamSelectorInventory.isTeamSelectorInventory(e.getInventory())) {
            e.getWhoClicked().sendMessage(teamSelectorInventory
                    .getTeamBySlot(e.getSlot())
                    .map(team -> team.getPlayers().add(gamePlayerStorage.getPlayer(e.getWhoClicked().getUniqueId())).description().get(0))
                    .orElse("Click a team item"));
        }
    }

    @EventHandler
    public void onTeamItemInteract(PlayerInteractEvent playerInteractEvent) {
        if (playerInteractEvent.hasItem() && playerInteractEvent.getItem().getType().equals(Material.COMPASS)) {
            teamSelectorInventory.displayTo(playerInteractEvent.getPlayer());
        }
    }
}
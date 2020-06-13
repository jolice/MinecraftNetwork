package com.github.jolice.game.team.selector;

import com.github.jolice.game.team.Team;
import lombok.Getter;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Wool;
import com.github.jolice.item.ItemStackBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Getter
public class TeamSelectorInventory {

    private Inventory inventory;
    private Map<Integer, Team> inventoryTeamPositions = new HashMap<>();

    public TeamSelectorInventory(List<Team> allTeams, Server server) {
        this.inventory = server.createInventory(null, 9, "Team selection");
        this.fill(allTeams);
    }

    private void fill(List<Team> allTeams) {
        for (int i = 0; i < allTeams.size(); i++) {
            Team team = allTeams.get(i);
            ItemStack teamItem = createTeamItem(team.getColor(), team.getName());
            this.inventory.setItem(i, teamItem);
            this.inventoryTeamPositions.put(i, team);
        }
    }

    private ItemStack createTeamItem(DyeColor dyeColor, String teamName) {
        return new ItemStackBuilder(Material.WOOL)
                .materialData(() -> new Wool(dyeColor))
                .displayName(teamName)
                .lore("Click to select team " + teamName)
                .build();
    }

    public boolean isTeamSelectorInventory(Inventory inventory) {
        return this.inventory == inventory;
    }

    public Optional<Team> getTeamBySlot(Integer slot) {
        return Optional.ofNullable(inventoryTeamPositions.get(slot));
    }

    public void displayTo(Player player) {
        player.openInventory(this.inventory);
    }
}

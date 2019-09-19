package io.riguron.bukkit.gui;

import io.riguron.bukkit.gui.multipage.SlotActions;
import io.riguron.bukkit.gui.multipage.player.PlayerPageRearranger;
import io.riguron.bukkit.listener.RegisterListener;
import lombok.RequiredArgsConstructor;
import org.bukkit.Server;
import org.bukkit.plugin.Plugin;
import io.riguron.bukkit.gui.multipage.*;
import io.riguron.bukkit.gui.multipage.player.PlayerPageRearranger;
import io.riguron.bukkit.gui.multipage.player.PlayerPages;
import io.riguron.bukkit.gui.single.SinglePageInterface;
import io.riguron.bukkit.gui.validate.SinglePageValidatingGui;
import io.riguron.bukkit.gui.validate.UniversalPageValidatingGui;
import io.riguron.bukkit.gui.validate.UniversalValidatingGui;
import io.riguron.bukkit.listener.RegisterListener;

/**
 * Factory responsible for creating different kinds of GUIs.
 */
@RequiredArgsConstructor
public class GUIFactory {

    private final Server server;
    private final RegisterListener registerListener;
    private final Plugin plugin;

    /**
     * Constructs multi page GUI.
     *
     * @param title title of the inventory
     * @return multi-page gui
     */
    public GUI newMultiPageGui(String title) {
        PlayerPages playerPages = new PlayerPages();
        PagesList pagesList = new PagesList();
        PageBuilder pageBuilder = new PageBuilder(title, server, pagesList);
        InventoryPages inventoryPages = new InventoryPages(pagesList, server, pageBuilder);
        SlotActions slotActions = new SlotActions(playerPages);
        PageSwitcher pageSwitcher = new PageSwitcher(inventoryPages, playerPages, server);
        MultiPageListener multiPageListener = new MultiPageListener(pageSwitcher, slotActions, playerPages);
        PlayerPageRearranger playerPageRearranger = new PlayerPageRearranger(playerPages, inventoryPages, server);
        registerListener.register(multiPageListener);
        return new UniversalValidatingGui(
                new UniversalPageValidatingGui(
                        new MultiPageGui(playerPages, inventoryPages, slotActions, server, playerPageRearranger)
                )
        );
    }

    /**
     * Constructs single-page GUI.
     *
     * @param title title of the inventory
     * @return single-page gui
     */
    public GUI newSinglePageGui(String title) {
        return new UniversalValidatingGui(
                new SinglePageValidatingGui(
                        new SinglePageInterface(
                                plugin,
                                title
                        )
                )
        );
    }
}

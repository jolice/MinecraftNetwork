package io.riguron.bukkit.gui.multipage;

import lombok.experimental.Delegate;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class PagesList implements List<Inventory> {

    @Delegate
    private List<Inventory> delegate = new ArrayList<>();
}

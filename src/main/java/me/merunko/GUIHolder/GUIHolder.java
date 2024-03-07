package me.merunko.GUIHolder;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

public class GUIHolder implements InventoryHolder {

    private final String instance;

    public GUIHolder(String instanceName) {
        instance = instanceName;
    }

    @Override
    public @NotNull Inventory getInventory() {
        return null;
    }

    public @NotNull String getInstance() {
        return instance;
    }
}

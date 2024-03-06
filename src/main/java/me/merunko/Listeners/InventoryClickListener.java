package me.merunko.Listeners;

import me.merunko.GUIHolder.GUIHolder;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Inventory clickedInventory = e.getClickedInventory();
        //InventoryCloseListener invCloseL = new InventoryCloseListener();
        int clickedSlot = e.getRawSlot();

        if (clickedInventory != null && clickedInventory.getHolder() instanceof GUIHolder) {

            if (!((clickedSlot >= 10 && clickedSlot <= 16) || (clickedSlot >= 19 && clickedSlot <= 25) || (clickedSlot >= 28 && clickedSlot <= 34) || (clickedSlot >= 37 && clickedSlot <= 43))) {
                e.setCancelled(true);
            }

        }
    }
}

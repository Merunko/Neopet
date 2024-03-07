package me.merunko.Listeners;

import me.merunko.Color.PoroColor;
import me.merunko.Configurations.MainConfiguration;
import me.merunko.GUIHolder.GUIHolder;

import me.merunko.GenericVariables.GenericVariables;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class InventoryClickListener implements Listener {

    private final MainConfiguration mainConfig;
    private final GenericVariables var;
    private final PoroColor pc;
    private final ConsoleCommandSender console = Bukkit.getConsoleSender();

    private static final int SUBMIT_BUTTON = 48;
    private static final int UPDATE_BUTTON = 49;
    private static final int CANCEL_BUTTON = 50;

    public InventoryClickListener(MainConfiguration mainConfig, GenericVariables var, PoroColor pc) {
        this.mainConfig = mainConfig;
        this.var = var;
        this.pc = pc;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        Inventory clickedInventory = e.getClickedInventory();
        InventoryCloseListener invCloseL = new InventoryCloseListener();

        if (clickedInventory != null && clickedInventory.getHolder() instanceof GUIHolder) {
            GUIHolder holder = (GUIHolder) clickedInventory.getHolder(); // Perform the cast here
            String instanceName = holder.getInstance();
            int clickedSlot = e.getRawSlot();

            if (!((clickedSlot >= 10 && clickedSlot <= 16) || (clickedSlot >= 19 && clickedSlot <= 25) || (clickedSlot >= 28 && clickedSlot <= 34) || (clickedSlot >= 37 && clickedSlot <= 43))) {
                e.setCancelled(true);

            }

            if (clickedSlot == CANCEL_BUTTON) {
                e.setCancelled(true);
                invCloseL.returnItems(e.getClickedInventory(), player.getInventory());
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0F, 1.0F);
            }

            if (clickedSlot == SUBMIT_BUTTON) {
                e.setCancelled(true);
                if (mainConfig.isSubmitPermRequired(instanceName) && !player.hasPermission(mainConfig.getSubmissionPermissions(instanceName))) {
                    pc.sendMessage(player, var.noSubmitPermissionMsg());
                    invCloseL.returnItems(e.getClickedInventory(), player.getInventory());
                    player.closeInventory();
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_PLACE, 1.0F, 1.0F);

                } else {
                    if (!mainConfig.checkInstanceSettingExistence(instanceName)) {
                        pc.sendMessage(player, var.noInstanceSettingFound(instanceName));
                        invCloseL.returnItems(e.getClickedInventory(), player.getInventory());
                        player.closeInventory();
                        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_PLACE, 1.0F, 1.0F);

                    } else {

                    }
                }
            }

        }
    }
}

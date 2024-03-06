package me.merunko.GUI;

import me.merunko.Color.PoroColor;
import me.merunko.Configurations.MainConfiguration;
import me.merunko.GUIHolder.GUIHolder;

import net.kyori.adventure.text.Component;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemSubmitterGUI {

    private final MainConfiguration mainConfig;
    private final PoroColor pc;

    private final String instance;

    public ItemSubmitterGUI(MainConfiguration mainConfig, PoroColor pc, String instance) {
        this.mainConfig = mainConfig;
        this.pc = pc;
        this.instance = instance;
    }

    public Inventory createItemSubmitterGUI(String instanceName) {
        Component title = mainConfig.getTitle(instanceName);
        int slot = 54;

        Inventory inventory = Bukkit.createInventory(new GUIHolder(), slot, title);

        ItemStack border = new ItemStack(mainConfig.getBorder(instanceName));
        ItemMeta borderMeta = border.getItemMeta();
        if (borderMeta != null) {
            borderMeta.displayName(Component.text(""));
            border.setItemMeta(borderMeta);
        }

        ItemStack submitButton = new ItemStack(Material.LIME_WOOL);
        ItemMeta submitButtonMeta = submitButton.getItemMeta();
        if (submitButtonMeta != null) {
            Component name1 = pc.translate("{00FB3F}Submit");
            submitButtonMeta.displayName(name1);
            submitButton.setItemMeta(submitButtonMeta);
        }

        ItemStack cancelButton = new ItemStack(Material.RED_WOOL);
        ItemMeta cancelButtonMeta = cancelButton.getItemMeta();
        if (cancelButtonMeta != null) {
            Component name2 = pc.translate("{FB5848}Cancel");
            cancelButtonMeta.displayName(name2);
            cancelButton.setItemMeta(cancelButtonMeta);
        }

        ItemStack boardSign = new ItemStack(mainConfig.getBoard(instanceName));
        ItemMeta boardSignMeta = boardSign.getItemMeta();
        if (boardSignMeta != null) {
            String name3_temp1 = mainConfig.getPointName(instanceName);
            String name3_temp2 = name3_temp1.concat("&r{FB5848} = &a0");
            Component name3 = pc.translate(name3_temp2);
            ConsoleCommandSender console = Bukkit.getConsoleSender();
            console.sendMessage(getInstanceName());
            boardSignMeta.displayName(name3);

            List<Component> lore = new ArrayList<>();
            lore.add(pc.translate(""));
            lore.add(pc.translate("{A45600}Click this to calculate total value."));
            boardSignMeta.lore(lore);

            boardSign.setItemMeta(boardSignMeta);
        }

        for (int i = 0; i < slot ; i++) {
            if (!((i >= 10 && i <= 16) || (i >= 19 && i <= 25) || (i >= 28 && i <= 34) || (i >= 37 && i <= 43))) {
                inventory.setItem(i, border);
            }
        }

        inventory.setItem(48, submitButton);
        inventory.setItem(50, cancelButton);
        inventory.setItem(49, boardSign);

        return inventory;
    }

    public String getInstanceName() {
        return instance;
    }

}

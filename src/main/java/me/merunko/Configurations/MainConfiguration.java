package me.merunko.Configurations;

import me.merunko.Color.PoroColor;
import me.merunko.GenericVariables.GenericVariables;

import net.kyori.adventure.text.Component;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class MainConfiguration {

    private final FileConfiguration config;
    private final GenericVariables var;
    private final PoroColor pc;
    private final ConsoleCommandSender console = Bukkit.getConsoleSender();

    public MainConfiguration(FileConfiguration config, GenericVariables var, PoroColor pc) {
        this.config = config;
        this.var = var;
        this.pc = pc;
    }

    public void load() {
        try {
            config.load(new File(var.getMainConfigPath()));
        } catch (IOException | InvalidConfigurationException e) {
            pc.sendMessage(console, var.getMainConfigReloadFailedMsg());
        }
    }

    public List<String> getInstances() {
        Set<String> instances = Objects.requireNonNull(config.getConfigurationSection("instances")).getKeys(false);
        return new ArrayList<>(instances);
    }

    public String getPointName(String instance) {
        String key = "instances." + instance + ".point-name";
        if (config.isString(key)) {
            return config.getString(key);
        } else {
            return "Nameless Point";
        }
    }

    public Component getTitle(String instance) {
        String key = "instances." + instance + ".gui-title";
        if (config.isString(key)) {
            return pc.translate(config.getString(key));
        } else {
            return pc.translate("No Title");
        }
    }

    public Material getBorder(String instance) {
        String key = "instances." + instance + ".gui-border";
        if (config.isString(key)) {
            String item = config.getString(key);
            if (item != null) {
                return Material.matchMaterial(item);
            }
        }
        return Material.AIR;
    }

    public Material getBoard(String instance) {
        String key = "instances." + instance + ".gui-board";
        if (config.isString(key)) {
            String item = config.getString(key);
            if (item != null) {
                return Material.matchMaterial(item);
            }
        }
        return Material.AIR;
    }

    public int getMMOItemValue(String itemType, String itemID) {
        if (config.isList("mmoitems." + itemType)) {
            List<String> items = config.getStringList("mmoitems." + itemType);
            for (String item : items) {
                String[] parts = item.split(":");
                if (parts.length == 2) {
                    String id = parts[0].trim();
                    int value = Integer.parseInt(parts[1].trim());
                    if (id.equals(itemID)) {
                        return value;
                    }
                }
            }
        }
        return 0;
    }

    public int getMinecraftItemValue(String itemName) {
        if (config.isConfigurationSection("minecraft_items")) {
            String key = "minecraft_items." + itemName;
            if (config.isInt(key)) {
                return config.getInt(key);
            }
        }
        return 0;
    }

}

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
        if (config.contains(key)) {
            String pointName = config.getString(key);
            if (pointName != null && !pointName.isEmpty()) {
                return pointName;
            }
        }
        return "Nameless Point";
    }

    public Component getTitle(String instance) {
        String key = "instances." + instance + ".gui-title";
        if (config.contains(key)) {
            String title = config.getString(key);
            if (title != null && !title.isEmpty()) {
                return pc.translate(title);
            }
        }
        return pc.translate("No Title");
    }

    public Material getMaterial(String instance, String section) {
        String key = "instances." + instance + "." + section;
        if (config.contains(key)) {
            String item = config.getString(key);
            if (item != null && !item.isEmpty()) {
                String[] parts = item.split(":");
                if (parts.length >= 1) {
                    Material material = Material.matchMaterial(parts[0]);
                    if (material != null) {
                        return material;
                    } else {
                        pc.sendMessage(console, var.invalidMaterialID(parts[0]));
                    }
                }
            }
        }
        return Material.AIR;
    }

    public int getCustomModelData(String instance, String section) {
        String key = "instances." + instance + "." + section;
        if (config.contains(key)) {
            String item = config.getString(key);
            if (item != null && !item.isEmpty()) {
                String[] parts = item.split(":");
                if (parts.length == 2) {
                    try {
                        int customModelData = Integer.parseInt(parts[1]);
                        if (customModelData < 0) {
                            pc.sendMessage(console, var.negativeCustomModelData(customModelData));
                        } else {
                            return customModelData;
                        }

                    } catch (NumberFormatException e) {
                        pc.sendMessage(console, var.invalidCustomModelData(parts[1]));
                    }
                }
            }
        }
        return 0;
    }

    public String getSubmissionPermissions(String instance) {
        String key = "instances." + instance + ".required-permission-to-submit";
        if (config.isString(key)) {
            return config.getString(key);
        } else {
            return null;
        }
    }

    public boolean isSubmitPermRequired(String instance) {
        String key = "instances." + instance + ".required-permission-to-submit";
        if (config.contains(key)) {
            String permission = config.getString(key);
            return permission != null && !permission.isEmpty();
        }
        return false;
    }

    public boolean checkInstanceSettingExistence(String instance) {
        String filePath = var.getInstanceConfigPath() + instance + ".yml";
        File file = new File(filePath);
        if (file.exists()) {
            return true;
        } else {
            pc.sendMessage(console, var.noInstanceSettingFound(instance));
            return false;
        }
    }

}

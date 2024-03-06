package me.merunko;

import me.merunko.Color.PoroColor;
import me.merunko.Commands.Commands;
import me.merunko.Commands.CommandsTabCompleter;
import me.merunko.Configurations.MainConfiguration;
import me.merunko.GenericVariables.GenericVariables;
import me.merunko.Listeners.InventoryClickListener;
import me.merunko.Listeners.InventoryCloseListener;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Objects;

public final class Neopet extends JavaPlugin {

    public static MainConfiguration mainConfig;
    public static GenericVariables var = new GenericVariables();
    public static PoroColor pc = new PoroColor();
    private final ConsoleCommandSender console = Bukkit.getConsoleSender();

    @Override
    public void onEnable() {
        loadConfig(var.getConfigFileName());
        registerCommands();
        registerEvents();
    }

    @Override
    public void onDisable() {

    }

    private void loadConfig(String fileName) {
        File configFile = new File(getDataFolder(), fileName);

        if (!configFile.exists()) {
            saveResource(fileName, false);
            pc.sendMessage(console, "{FB7D7D}Can't find {FBAE48}" + fileName + "{FB7D7D}, generating {FBAE48}" + fileName + "{FB7D7D}.");
        }

        FileConfiguration fileConfig = YamlConfiguration.loadConfiguration(configFile);

        if (fileName.equalsIgnoreCase(var.getConfigFileName())) {
            mainConfig = new MainConfiguration(fileConfig, var, pc);
            mainConfig.load();
            pc.sendMessage(console, var.getMainConfigLoadedMsg());
        }
    }

    private void registerCommands() {
        Objects.requireNonNull(getCommand("neopet")).setExecutor(new Commands(mainConfig, var, pc));
        Objects.requireNonNull(getCommand("neopet")).setTabCompleter(new CommandsTabCompleter(mainConfig));
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new InventoryCloseListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
    }
}

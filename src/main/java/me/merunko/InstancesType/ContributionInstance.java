package me.merunko.InstancesType;

import me.merunko.Color.PoroColor;
import me.merunko.GenericVariables.GenericVariables;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;

public class ContributionInstance {

    private final FileConfiguration contributionInstanceConfig;
    private final GenericVariables var;
    private final PoroColor pc;
    private final ConsoleCommandSender console = Bukkit.getConsoleSender();

    public ContributionInstance(FileConfiguration contributionInstanceConfig, GenericVariables var, PoroColor pc) {
        this.contributionInstanceConfig = contributionInstanceConfig;
        this.var = var;
        this.pc = pc;
    }

    public void load() {
        try {
            contributionInstanceConfig.load(new File(var.getMainConfigPath()));
        } catch (IOException | InvalidConfigurationException e) {
            pc.sendMessage(console, var.getMainConfigReloadFailedMsg());
        }
    }

}

package me.merunko.Commands;

import me.merunko.Color.PoroColor;
import me.merunko.Configurations.MainConfiguration;
import me.merunko.GUI.ItemSubmitterGUI;
import me.merunko.GenericVariables.GenericVariables;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Commands implements CommandExecutor {

    private final MainConfiguration mainConfig;
    private final GenericVariables var;
    private final PoroColor pc;
    private final ConsoleCommandSender console = Bukkit.getConsoleSender();

    public Commands(MainConfiguration mainConfig, GenericVariables var, PoroColor pc) {
        this.mainConfig = mainConfig;
        this.var = var;
        this.pc = pc;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        if (args.length == 0) {
            return true;

        } else if (args.length == 1 && args[0].equals("reload")) {
            handleReloadCommand(commandSender);
            return true;

        } else if (args.length == 3 && args[0].equalsIgnoreCase("open")) {
            handleOpenCommand((Player) commandSender, args[2], args[1]);
            return true;

        }

        return false;

    }

    private void handleReloadCommand(CommandSender sender) {
        if (sender.hasPermission("neopet.reload")) {
            mainConfig.load();
            pc.sendMessage(sender, var.getMainConfigReloadSuccessMsg());

        } else {
            pc.sendMessage(sender, var.getNoPermissionMsg());
        }
    }

    private void handleOpenCommand(Player player, String targetPlayerName, String instance) {
        Player targetPlayer = player.getServer().getPlayer(targetPlayerName);

        if (targetPlayer != null) {
            ItemSubmitterGUI gui = new ItemSubmitterGUI(mainConfig, pc, instance);
            targetPlayer.openInventory(gui.createItemSubmitterGUI(instance));
        } else {
            pc.sendMessage(console, var.invalidPlayer(targetPlayerName));
        }
    }

}

package me.merunko.Commands;

import me.merunko.Configurations.MainConfiguration;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CommandsTabCompleter implements TabCompleter {

    private final MainConfiguration mainConfig;

    public CommandsTabCompleter(MainConfiguration mainConfig) {
        this.mainConfig = mainConfig;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            completions.addAll(Arrays.asList("reload", "open"));

        } else if (args.length == 2) {
            String subcommand = args[0];
            switch (subcommand) {
                case "open":
                    completions.addAll(mainConfig.getInstances());
                    break;
            }
        } else if (args.length == 3 && args[0].equalsIgnoreCase("open")) {
            completions.add("%player%");
            completions.addAll(Bukkit.getOnlinePlayers().stream().map(Player::getName).collect(Collectors.toList()));

        }

        String input = args[args.length - 1].toLowerCase();
        return completions.stream()
                .filter(completion -> completion.toLowerCase().startsWith(input))
                .collect(Collectors.toList());

    }
}

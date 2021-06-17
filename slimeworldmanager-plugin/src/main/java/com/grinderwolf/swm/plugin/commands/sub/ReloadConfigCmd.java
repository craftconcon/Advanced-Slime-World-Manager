package com.grinderwolf.swm.plugin.commands.sub;


import com.grinderwolf.swm.plugin.config.DatasourcesConfig;
import com.grinderwolf.swm.plugin.config.MainConfig;
import com.grinderwolf.swm.plugin.config.WorldsConfig;
import com.grinderwolf.swm.plugin.log.Logging;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.Plugin;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Getter
public class ReloadConfigCmd implements Subcommand {

    private final String usage = "reload";
    private final String description = "Reloads the config files.";
    private final String permission = "swm.reload";

    private final Plugin plugin;

    public ReloadConfigCmd(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, String[] args) {
        MainConfig.loadConfig(this.plugin);
        WorldsConfig.loadConfig(this.plugin);
        DatasourcesConfig.loadConfig(this.plugin);

        sender.sendMessage(Logging.COMMAND_PREFIX + ChatColor.GREEN + "Config reloaded.");

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return Collections.emptyList();
    }
}


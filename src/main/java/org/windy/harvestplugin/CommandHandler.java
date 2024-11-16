package org.windy.harvestplugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.windy.harvestplugin.Harvestplugin;

public class CommandHandler implements CommandExecutor {
    private final Harvestplugin plugin;

    public CommandHandler(Harvestplugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            plugin.reloadConfig();
            sender.sendMessage("配置已重新加载！");
            return true;
        }
        sender.sendMessage("用法: /harvestplugin reload");
        return false;
    }
}

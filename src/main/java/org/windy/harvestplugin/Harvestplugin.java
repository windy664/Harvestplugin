package org.windy.harvestplugin;

import org.bukkit.plugin.java.JavaPlugin;
import org.windy.harvestplugin.CommandHandler;
import org.windy.harvestplugin.ConfigManager;
import org.windy.harvestplugin.HarvestEventListener;

public class Harvestplugin extends JavaPlugin {
    private ConfigManager configManager;
    private static Harvestplugin instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        this.configManager = new ConfigManager(this);

        getServer().getPluginManager().registerEvents(new HarvestEventListener(this), this);
        getCommand("harvestplugin").setExecutor(new CommandHandler(this));

        getLogger().info("HarvestPlugin enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("HarvestPlugin disabled!");
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }
    public static Harvestplugin getInstance() {
        return instance;
    }
}

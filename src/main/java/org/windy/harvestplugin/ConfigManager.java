package org.windy.harvestplugin;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;

public class ConfigManager {
    private final Harvestplugin plugin;
    private final FileConfiguration config;

    public ConfigManager(Harvestplugin plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
        plugin.saveDefaultConfig();
    }

    public boolean requireHoe() {
        return config.getBoolean("require_hoe", false);
    }

    public int getDamageOnHarvest() {
        return config.getInt("damage_on_harvest", 1);
    }

    public int getExpOnHarvest() {
        return config.getInt("exp_on_harvest", 5);
    }

    public boolean playSound() {
        return config.getBoolean("play_sound", true);
    }

    public long getCooldownTime() {
        return config.getLong("cooldown_time", 2) * 1000L; // 转换为毫秒
    }

    // 解析收入加成配置
    public Map<String, Double> getIncomeBonus() {
        Map<String, Double> incomeBonusMap = new HashMap<>();
        if (config.isConfigurationSection("income_bonus")) {
            for (String key : config.getConfigurationSection("income_bonus").getKeys(false)) {
                incomeBonusMap.put(key, config.getDouble("income_bonus." + key, 0.0));
            }
        }
        return incomeBonusMap;
    }
    public boolean isDebugModeEnabled() {
        return config.getBoolean("debug-mode", false);  // 默认为false，若配置为true则启用debug模式
    }
}

package org.windy.harvestplugin;

import org.bukkit.entity.Player;

import java.util.Map;

public class PermissionManager {
    private final Map<String, Double> incomeBonus;

    public PermissionManager(Map<String, Double> incomeBonus) {
        this.incomeBonus = incomeBonus;
    }

    // 检查玩家是否有权限收获
    public boolean hasHarvestPermission(Player player) {
        return player.hasPermission("harvest.use");
    }

    // 获取玩家的收入加成
    public double getIncomeBonus(Player player) {
        for (String group : incomeBonus.keySet()) {
            if (player.hasPermission("harvest.income." + group)) {
                return incomeBonus.get(group);
            }
        }
        return incomeBonus.getOrDefault("default", 1.0);
    }
}

package org.windy.harvestplugin;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CropHandler {
    private final Harvestplugin plugin;
    private final ConfigManager configManager;

    public CropHandler(Harvestplugin plugin) {
        this.plugin = plugin;
        this.configManager = plugin.getConfigManager();
    }

    public boolean canHarvest(Block block) {
        Material material = block.getType();

        // 如果开启了 debug 模式，打印作物类型信息
        if (configManager.isDebugModeEnabled()) {
            System.out.println("Debug: 检查作物类型 - " + material.name());
        }

        // 检查是否是可收获的作物
        return isHarvestableCrop(material);
    }

    private boolean isHarvestableCrop(Material material) {
        // 检查原版作物以及模组添加的作物，支持 _CROP 后缀
        boolean isHarvestable = switch (material) {
            case WHEAT, CARROTS, POTATOES, BEETROOTS, NETHER_WART, COCOA -> true;
            default -> material.name().endsWith("_CROP");
        };

        // 打印调试信息（如果启用了 debug 模式）
        if (configManager.isDebugModeEnabled()) {
            System.out.println("Debug: 作物是否可收获 - " + material.name() + ": " + isHarvestable);
        }

        return isHarvestable;
    }

    public void harvestCrop(Block block, Player player, double incomeBonus) {
        if (!canHarvest(block)) {
            return;
        }
        if (configManager.isDebugModeEnabled()) {
            System.out.println("Debug: 收割作物 - " + block.getType().name());
        }

        // 这里只是示意，实际收获操作可能需要根据作物类型和行为进一步定义
    }

    public int getHarvestRadius(ItemStack tool) {
        // 计算收割半径的逻辑
        return 1; // 示例，实际逻辑可以根据工具及配置决定
    }
}

package org.windy.harvestplugin;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.TileState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class HarvestEventListener implements Listener {
    private final CropHandler cropHandler;
    private final PermissionManager permissionManager;

    public HarvestEventListener(Harvestplugin plugin) {
        this.cropHandler = new CropHandler(plugin);
        this.permissionManager = new PermissionManager(plugin.getConfigManager().getIncomeBonus());
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();

        if (block != null && cropHandler.canHarvest(block)) {
            // 检查权限
            if (!permissionManager.hasHarvestPermission(player)) {
                player.sendMessage("你没有权限收获作物！");
                return;
            }

            event.setCancelled(true);

            // 获取手持工具和收割半径
            ItemStack tool = player.getInventory().getItemInMainHand();
            int radius = cropHandler.getHarvestRadius(tool);

            // 获取收入加成
            double incomeBonus = permissionManager.getIncomeBonus(player);

            // 调试模式输出
            if (Harvestplugin.getInstance().getConfigManager().isDebugModeEnabled()) {
                System.out.println("Debug: 玩家 " + player.getName() + " 正在尝试与方块交互");
                System.out.println("Debug: 作物类型 - " + block.getType().name());

                // 获取 BlockState
                BlockState blockState = block.getState();

                // 如果方块是 TileState（如箱子、熔炉等）
                if (blockState instanceof TileState) {
                    TileState tileState = (TileState) blockState;

                    // 通过 NBTAPI 获取 TileEntity 的 NBT 数据
                    NBTItem nbtItem = new NBTItem((ItemStack) tileState.getBlock());
                    NBTCompound nbt = (NBTCompound) nbtItem.getCompound();

                    // 输出方块的 NBT 数据
                    System.out.println("Debug: 当前方块的 NBT 数据 - " + nbt.toString());
                } else {
                    // 如果方块没有 TileEntity，输出方块基本信息
                    System.out.println("Debug: 当前方块没有 TileEntity，没有额外的 NBT 数据");
                }
            }

            // 收割作物（按范围）
            for (int x = -radius; x <= radius; x++) {
                for (int z = -radius; z <= radius; z++) {
                    Block nearbyBlock = block.getRelative(x, 0, z);
                    cropHandler.harvestCrop(nearbyBlock, player, incomeBonus);
                }
            }
        }
    }
}






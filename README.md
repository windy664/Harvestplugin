<div align="center">

<img src="assets/banner.svg" alt="Harvestplugin" width="100%">

# Harvestplugin

**右键范围收割** —— 右键成熟作物一键收割周围一圈，锄头越好范围越大；支持收获经验、耐久损耗与权限收益加成。

![Platform](https://img.shields.io/badge/Platform-Spigot%20%2F%20Paper-62bda8?style=flat-square&logo=minecraft&logoColor=white)
![Minecraft](https://img.shields.io/badge/MC-1.21.x-8a9a5b?style=flat-square)
![Java](https://img.shields.io/badge/Java-21-f89820?style=flat-square&logo=openjdk&logoColor=white)

[功能](#-功能) · [配置](#️-配置)

</div>

## ✨ 功能

- **范围收割**：右键成熟作物，按半径收割周围所有成熟作物（`CropHandler` 处理掉落与补种）
- **锄头分级半径**：木 1 / 石 2 / 铁 3 / 钻石 4 / 下界合金 5 格，`harvest-radius` 可配
- **收益加成**：`income-bonus` 按权限给额外产出 —— `vip` +20%、`premium` +50%（`PermissionManager` 判定）
- **代价与奖励**：`damage-on-harvest` 给锄头掉耐久，`exp-on-harvest` 给经验，`play-sound` 收割音效
- **冷却**：`cooldown_time` 防连点；`crops` 留空即全作物生效，也可白名单指定

## ⚙️ 配置

```yaml
require-hoe: false
damage-on-harvest: 1
exp-on-harvest: 5
play-sound: true
crops: []                          # 留空 = 全部作物

harvest-radius:
  WOODEN_HOE: 1
  STONE_HOE: 2
  IRON_HOE: 3
  DIAMOND_HOE: 4
  NETHERITE_HOE: 5

cooldown_time: 2

income-bonus:
  default: 0
  vip: 0.2
  premium: 0.5
```

## 🔨 构建

```bash
./gradlew build
```

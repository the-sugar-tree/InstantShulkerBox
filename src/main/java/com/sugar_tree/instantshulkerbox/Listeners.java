package com.sugar_tree.instantshulkerbox;

import org.bukkit.Material;
import org.bukkit.block.ShulkerBox;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Listeners implements Listener {
    @EventHandler
    public void onInvClose(InventoryCloseEvent event) {
        if (event.getInventory().getType().equals(InventoryType.SHULKER_BOX)) {
            if (map.containsKey(event.getPlayer().getUniqueId())) {
                ItemStack i = map.get(event.getPlayer().getUniqueId());
                if (i.getItemMeta() == null) {
                    return;
                }
                BlockStateMeta meta = ((BlockStateMeta) i.getItemMeta());
                ShulkerBox box = ((ShulkerBox) meta.getBlockState());
                box.getInventory().setContents(event.getInventory().getContents());
                meta.setBlockState(box);
                map.get(event.getPlayer().getUniqueId()).setItemMeta(meta);
                map.remove(event.getPlayer().getUniqueId());
            }
        }
    }

    @EventHandler
    public void onRightClickBox(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        if (!p.isSneaking()) {
            return;
        }
        switch (event.getAction()) {
            case RIGHT_CLICK_AIR:
            case RIGHT_CLICK_BLOCK:
                break;
            default:
                return;
        }
        ItemStack hand = p.getInventory().getItemInMainHand();
        if (!isShulkerBox(hand.getType())) {
            return;
        }
        event.setCancelled(true);
        openShulkerInv(event.getPlayer());
    }

    final Map<UUID, ItemStack> map = new HashMap<>();

    @SuppressWarnings("ConstantConditions")
    public void openShulkerInv(Player p) {
        ItemStack i = p.getInventory().getItemInMainHand();
        ShulkerBox box = (ShulkerBox) ((BlockStateMeta) i.getItemMeta()).getBlockState();
        Inventory inv = box.getInventory();
        p.openInventory(inv);
        map.put(p.getUniqueId(), i);
    }

    private boolean isShulkerBox(Material material) {
        switch (material) {
            case SHULKER_BOX:
            case BLACK_SHULKER_BOX:
            case BLUE_SHULKER_BOX:
            case BROWN_SHULKER_BOX:
            case CYAN_SHULKER_BOX:
            case LIGHT_GRAY_SHULKER_BOX:
            case GRAY_SHULKER_BOX:
            case GREEN_SHULKER_BOX:
            case LIGHT_BLUE_SHULKER_BOX:
            case LIME_SHULKER_BOX:
            case MAGENTA_SHULKER_BOX:
            case ORANGE_SHULKER_BOX:
            case PINK_SHULKER_BOX:
            case PURPLE_SHULKER_BOX:
            case RED_SHULKER_BOX:
            case WHITE_SHULKER_BOX:
            case YELLOW_SHULKER_BOX:
                return true;
            default:
                return false;
        }
    }

}

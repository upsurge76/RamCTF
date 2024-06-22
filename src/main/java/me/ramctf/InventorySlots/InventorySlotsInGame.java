package me.ramctf.InventorySlots;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class InventorySlotsInGame {
    
    public static ItemStack getEndGameSlot(){
        ItemStack redstoneBlock = new ItemStack(Material.REDSTONE_BLOCK);
        ItemMeta redstoneBlockMeta = redstoneBlock.getItemMeta();
        redstoneBlockMeta.setDisplayName(ChatColor.WHITE + "End Game");
        redstoneBlock.setItemMeta(redstoneBlockMeta);
        return redstoneBlock;
    }
        
    public static ItemStack getGlassPane(){
        ItemStack glasspane = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta glasspaneMeta = glasspane.getItemMeta();
        glasspaneMeta.setDisplayName(" ");
        glasspane.setItemMeta(glasspaneMeta);
        return glasspane;
    }

}

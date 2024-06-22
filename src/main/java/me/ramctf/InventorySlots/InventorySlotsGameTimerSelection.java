package me.ramctf.InventorySlots;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.ramctf.GameProperties;
import net.md_5.bungee.api.ChatColor;

public class InventorySlotsGameTimerSelection {
    
    public static ItemStack getLabeledGlassPane(int time){
        if(time == GameProperties.gameTimer()){
            ItemStack glass = new ItemStack(Material.GREEN_STAINED_GLASS);
            ItemMeta glassMeta = glass.getItemMeta();
            glassMeta.setDisplayName(ChatColor.WHITE + String.valueOf(time) + " Minutes");
            glass.setItemMeta(glassMeta);
            return glass;
        } else {
            ItemStack glass = new ItemStack(Material.BLACK_STAINED_GLASS);
            ItemMeta glassMeta = glass.getItemMeta();
            glassMeta.setDisplayName(ChatColor.WHITE + String.valueOf(time) + " Minutes");
            glass.setItemMeta(glassMeta);
            return glass;
        }
    }

    public static ItemStack getTimeOptions(int index, int[] timeOptions){
        return getLabeledGlassPane(timeOptions[index]);
    }
        
    public static ItemStack getGlassPane(){
        ItemStack glasspane = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta glasspaneMeta = glasspane.getItemMeta();
        glasspaneMeta.setDisplayName(" ");
        glasspane.setItemMeta(glasspaneMeta);
        return glasspane;
    }

}

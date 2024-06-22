package me.ramctf.InventorySlots;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import net.md_5.bungee.api.ChatColor;

public class InventorySlotsSettings {
    
    public static ItemStack getSettingsBackSlot(){
        ItemStack enderEye = new ItemStack(Material.ENDER_EYE);
        ItemMeta enderEyeMeta = enderEye.getItemMeta();
        enderEyeMeta.setDisplayName(ChatColor.WHITE + "Back");
        enderEye.setItemMeta(enderEyeMeta);
        return enderEye;
    }

    public static ItemStack getSettingsPregameTimeSlot(){
        ItemStack clock = new ItemStack(Material.CLOCK);
        ItemMeta clockMeta = clock.getItemMeta();
        clockMeta.setDisplayName(ChatColor.WHITE + "Game Start Delay");
        clock.setItemMeta(clockMeta);
        return clock;
    }

    public static ItemStack getSettingsGameTimeSlot(){
        ItemStack clock = new ItemStack(Material.CLOCK);
        ItemMeta clockMeta = clock.getItemMeta();
        clockMeta.setDisplayName(ChatColor.WHITE + "Game Duration");
        clock.setItemMeta(clockMeta);
        return clock;
    }

    public static ItemStack getSettingsPregameTimeInfoSlot(){
        ItemStack blueStainedGlassPane = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
        ItemMeta blueStainedGlassPaneMeta = blueStainedGlassPane.getItemMeta();
        blueStainedGlassPaneMeta.setDisplayName(ChatColor.WHITE + "Amount of time for resource collection before game starts");
        blueStainedGlassPane.setItemMeta(blueStainedGlassPaneMeta);
        return blueStainedGlassPane;
    }
    public static ItemStack getSettingsGameTimeInfoSlot(){
        ItemStack blueStainedGlassPane = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
        ItemMeta blueStainedGlassPaneMeta = blueStainedGlassPane.getItemMeta();
        blueStainedGlassPaneMeta.setDisplayName(ChatColor.WHITE + "Amount of time for resource collection");
        blueStainedGlassPane.setItemMeta(blueStainedGlassPaneMeta);
        return blueStainedGlassPane;
    }
    public static ItemStack getGlassPane(){
        ItemStack glasspane = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta glasspaneMeta = glasspane.getItemMeta();
        glasspaneMeta.setDisplayName(" ");
        glasspane.setItemMeta(glasspaneMeta);
        return glasspane;
    }

}

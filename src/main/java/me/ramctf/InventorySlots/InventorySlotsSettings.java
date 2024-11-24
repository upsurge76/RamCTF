package me.ramctf.InventorySlots;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.ramctf.GameProperties;
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
        clockMeta.setDisplayName(ChatColor.WHITE + "Pregame Duration");
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

    public static ItemStack getSettingsPlayerLootDrop(){
        ItemStack ironOre = new ItemStack(Material.IRON_ORE);
        ItemMeta ironOreMeta = ironOre.getItemMeta();
        ironOreMeta.setDisplayName(ChatColor.WHITE + "Should players drop loot if they get killed close to enemy flag during pregame");
        ironOre.setItemMeta(ironOreMeta);
        return ironOre;
    }

    public static ItemStack getSettingsFlagBuildProtection(){
        ItemStack grassBlock = new ItemStack(Material.GRASS_BLOCK);
        ItemMeta grassBlockMeta = grassBlock.getItemMeta();
        grassBlockMeta.setDisplayName(ChatColor.WHITE + "Prevent building or breaking near flags");
        grassBlock.setItemMeta(grassBlockMeta);
        return grassBlock;
    }

    public static ItemStack getSettingsHungerSlot(){
        ItemStack cookedSteak = new ItemStack(Material.COOKED_BEEF);
        ItemMeta cookedSteakMeta = cookedSteak.getItemMeta();
        cookedSteakMeta.setDisplayName(ChatColor.WHITE + "Do hunger in pregame");
        cookedSteak.setItemMeta(cookedSteakMeta);
        return cookedSteak;
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

    public static ItemStack getSettingsPlayerLootDropInfoSlot(){
        if(GameProperties.pregamePlayerLootDrop()){
            ItemStack greenStainedGlassPane = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
            ItemMeta greenStainedGlassPaneMeta = greenStainedGlassPane.getItemMeta();
            greenStainedGlassPaneMeta.setDisplayName(ChatColor.WHITE + "Player loot drop is enabled");
            greenStainedGlassPane.setItemMeta(greenStainedGlassPaneMeta);
            return greenStainedGlassPane;
        } else {
            ItemStack redStainedGlassPane = new ItemStack(Material.RED_STAINED_GLASS_PANE);
            ItemMeta redStainedGlassPaneMeta = redStainedGlassPane.getItemMeta();
            redStainedGlassPaneMeta.setDisplayName(ChatColor.WHITE + "Player loot drop is disabled");
            redStainedGlassPane.setItemMeta(redStainedGlassPaneMeta);
            return redStainedGlassPane;
        }
    }

    public static ItemStack getSettingsFlagBuildProtectionInfoSlot(){
        if(GameProperties.flagBuildProtection()){
            ItemStack greenStainedGlassPane = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
            ItemMeta greenStainedGlassPaneMeta = greenStainedGlassPane.getItemMeta();
            greenStainedGlassPaneMeta.setDisplayName(ChatColor.WHITE + "Flag build protection is enabled");
            greenStainedGlassPane.setItemMeta(greenStainedGlassPaneMeta);
            return greenStainedGlassPane;
        } else {
            ItemStack redStainedGlassPane = new ItemStack(Material.RED_STAINED_GLASS_PANE);
            ItemMeta redStainedGlassPaneMeta = redStainedGlassPane.getItemMeta();
            redStainedGlassPaneMeta.setDisplayName(ChatColor.WHITE + "Flag build protection is disabled");
            redStainedGlassPane.setItemMeta(redStainedGlassPaneMeta);
            return redStainedGlassPane;
        }
    }

    public static ItemStack getSettingsHungerInfoSlot(){
        if(GameProperties.doPregameHunger()){
            ItemStack greenStainedGlassPane = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
            ItemMeta greenStainedGlassPaneMeta = greenStainedGlassPane.getItemMeta();
            greenStainedGlassPaneMeta.setDisplayName(ChatColor.WHITE + "Hunger in pregame is enabled");
            greenStainedGlassPane.setItemMeta(greenStainedGlassPaneMeta);
            return greenStainedGlassPane;
        } else {
            ItemStack redStainedGlassPane = new ItemStack(Material.RED_STAINED_GLASS_PANE);
            ItemMeta redStainedGlassPaneMeta = redStainedGlassPane.getItemMeta();
            redStainedGlassPaneMeta.setDisplayName(ChatColor.WHITE + "Hunger in pregame is disabled");
            redStainedGlassPane.setItemMeta(redStainedGlassPaneMeta);
            return redStainedGlassPane;
        }
    }

    public static ItemStack getGlassPane(){
        ItemStack glasspane = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta glasspaneMeta = glasspane.getItemMeta();
        glasspaneMeta.setDisplayName(" ");
        glasspane.setItemMeta(glasspaneMeta);
        return glasspane;
    }

}

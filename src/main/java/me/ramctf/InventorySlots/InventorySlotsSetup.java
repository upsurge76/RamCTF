package me.ramctf.InventorySlots;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import me.ramctf.GameManager;
import me.ramctf.GameProperties;
import net.md_5.bungee.api.ChatColor;

public class InventorySlotsSetup {

    public static ItemStack getBlueFlagSlot(){

        ItemStack blueBanner = new ItemStack(Material.BLUE_BANNER);
        ItemMeta blueBannerMeta = blueBanner.getItemMeta();
        blueBannerMeta.setDisplayName(ChatColor.BLUE + "Set Blue Flag Location");
        blueBanner.setItemMeta(blueBannerMeta);
        return blueBanner;

    }

    public static ItemStack getStartButton(){

        if(GameManager.isGameReadyToStart()){
            ItemStack startLever = new ItemStack(Material.LEVER);
            ItemMeta startLeverMeta = startLever.getItemMeta();
            startLeverMeta.setDisplayName(ChatColor.GREEN + "Start Game");
            startLeverMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
            startLeverMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            startLever.setItemMeta(startLeverMeta);
            return startLever;

        } else {
            ItemStack startLever = new ItemStack(Material.LEVER);
            ItemMeta startLeverMeta = startLever.getItemMeta();
            startLeverMeta.setDisplayName(ChatColor.RED + "Game Not Ready To Start");
            startLever.setItemMeta(startLeverMeta);
            return startLever;
        
        }

    }

    public static ItemStack getRedFlagSlot(){
        ItemStack redBanner = new ItemStack(Material.RED_BANNER);
        ItemMeta redBannerMeta = redBanner.getItemMeta();
        redBannerMeta.setDisplayName(ChatColor.RED + "Set Red Flag Location");
        redBanner.setItemMeta(redBannerMeta);
        return redBanner;

    }

    public static ItemStack getSettingsSlot(){
        ItemStack settings = new ItemStack(Material.COMPASS);
        ItemMeta settingsMeta = settings.getItemMeta();
        settingsMeta.setDisplayName(ChatColor.WHITE + "Game Settings");
        settings.setItemMeta(settingsMeta);
        return settings;
    }


    public static ItemStack getTeamSetupSlot(){
        if (GameProperties.teamSetupCompleted == false){
            if (GameProperties.teamSetupStarted == false){
                ItemStack leatherChestPlate = new ItemStack(Material.LEATHER_CHESTPLATE);
                LeatherArmorMeta leatherchestplateMeta = (LeatherArmorMeta) leatherChestPlate.getItemMeta();
                leatherchestplateMeta.setDisplayName(ChatColor.WHITE + "Begin Team Setup");
                leatherchestplateMeta.setColor(Color.WHITE);
                leatherChestPlate.setItemMeta(leatherchestplateMeta);
                return leatherChestPlate;
            } else {

                ItemStack enchantedChestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
                LeatherArmorMeta enchantedChestplateMeta = (LeatherArmorMeta) enchantedChestplate.getItemMeta();
                enchantedChestplateMeta.setDisplayName(ChatColor.GREEN + "Team Setup In Progress");
                enchantedChestplateMeta.setColor(Color.GREEN);
                enchantedChestplateMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true); 
                enchantedChestplateMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS); 
                enchantedChestplate.setItemMeta(enchantedChestplateMeta);

                return enchantedChestplate;
            }

        } else {
            ItemStack greenChestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
            LeatherArmorMeta greenChestplateMeta = (LeatherArmorMeta) greenChestplate.getItemMeta();
            greenChestplateMeta.setDisplayName(ChatColor.GREEN + "Team Setup Completed");
            greenChestplateMeta.setColor(Color.GREEN);
            greenChestplate.setItemMeta(greenChestplateMeta);
            return greenChestplate;
        }
    }

    public static ItemStack getRedFlagStatus(){
        if(GameProperties.redFlagLocationBase == null){
            ItemStack redGlassPane = new ItemStack(Material.RED_STAINED_GLASS_PANE);
            ItemMeta redGlassPaneMeta = redGlassPane.getItemMeta();
            redGlassPaneMeta.setDisplayName(ChatColor.RED + "Red Flag Location Not Set");
            redGlassPane.setItemMeta(redGlassPaneMeta);
            return redGlassPane;
        } else {
            ItemStack greenGlassPane = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
            ItemMeta greenGlassPaneMeta = greenGlassPane.getItemMeta();
            greenGlassPaneMeta.setDisplayName(ChatColor.GREEN + "Red Flag Location Set");
            greenGlassPane.setItemMeta(greenGlassPaneMeta);
            return greenGlassPane;
        }
    }

    public static ItemStack getBlueFlagStatus(){
        if(GameProperties.blueFlagLocationBase == null){
            ItemStack redGlassPane = new ItemStack(Material.RED_STAINED_GLASS_PANE);
            ItemMeta redGlassPaneMeta = redGlassPane.getItemMeta();
            redGlassPaneMeta.setDisplayName(ChatColor.RED + "Blue Flag Location Not Set");
            redGlassPane.setItemMeta(redGlassPaneMeta);
            return redGlassPane;
        } else {
            ItemStack greenGlassPane = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
            ItemMeta greenGlassPaneMeta = greenGlassPane.getItemMeta();
            greenGlassPaneMeta.setDisplayName(ChatColor.GREEN + "Blue Flag Location Set");
            greenGlassPane.setItemMeta(greenGlassPaneMeta);
            return greenGlassPane;
        }
    }

    public static ItemStack getTeamSetupStatus(){
        if(!GameProperties.teamSetupStarted && !GameProperties.teamSetupCompleted){
            ItemStack redGlassPane = new ItemStack(Material.RED_STAINED_GLASS_PANE);
            ItemMeta redGlassPaneMeta = redGlassPane.getItemMeta();
            redGlassPaneMeta.setDisplayName(ChatColor.WHITE + "Team Setup Not Started");
            redGlassPane.setItemMeta(redGlassPaneMeta);
            return redGlassPane;

        } else if(!GameProperties.teamSetupCompleted && GameProperties.teamSetupStarted){
            ItemStack yellowGlassPane = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
            ItemMeta yellowGlassPaneMeta = yellowGlassPane.getItemMeta();
            yellowGlassPaneMeta.setDisplayName(ChatColor.GREEN + "Waiting for all players to pick teams");
            yellowGlassPane.setItemMeta(yellowGlassPaneMeta);
            return yellowGlassPane;

        } else {
            ItemStack greenGlassPane = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
            ItemMeta greenGlassPaneMeta = greenGlassPane.getItemMeta();
            greenGlassPaneMeta.setDisplayName(ChatColor.GREEN + "Team Setup Completed");
            greenGlassPane.setItemMeta(greenGlassPaneMeta);
            return greenGlassPane;
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

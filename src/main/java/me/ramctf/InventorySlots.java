package me.ramctf;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import net.md_5.bungee.api.ChatColor;

public class InventorySlots {

    public static ItemStack getSetupSlot1(){
        if(GameProperties.bluePlatformLocation == null){
            ItemStack blueBanner = new ItemStack(Material.BLUE_BANNER);
            ItemMeta blueBannerMeta = blueBanner.getItemMeta();
            blueBannerMeta.setDisplayName(ChatColor.BLUE + "Set Blue Flag Location");
            blueBanner.setItemMeta(blueBannerMeta);
            return blueBanner;
        } else if(GameProperties.bluePlatformSpawned == false) {
            ItemStack enchantedBlueWool = new ItemStack(Material.BLUE_WOOL);
            ItemMeta enchantedBlueWoolMeta = enchantedBlueWool.getItemMeta();
            enchantedBlueWoolMeta.setDisplayName(ChatColor.BLUE + "Spawn Blue Platform");
            enchantedBlueWoolMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
            enchantedBlueWoolMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS); 
            enchantedBlueWool.setItemMeta(enchantedBlueWoolMeta);
            return enchantedBlueWool;
        } else {
            ItemStack greenGlassPane = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
            ItemMeta greenGlassPaneMeta = greenGlassPane.getItemMeta();
            greenGlassPaneMeta.setDisplayName(ChatColor.BLUE + "Blue Platform Successfully Spawned");
            greenGlassPane.setItemMeta(greenGlassPaneMeta);
            return greenGlassPane;
        }

    }

    public static ItemStack getSetupSlot2(){
        if(GameProperties.redPlatformLocation == null){
            ItemStack redBanner = new ItemStack(Material.RED_BANNER);
            ItemMeta redBannerMeta = redBanner.getItemMeta();
            redBannerMeta.setDisplayName(ChatColor.RED + "Set Red Flag Location");
            redBanner.setItemMeta(redBannerMeta);
            return redBanner;
        } else if(GameProperties.redPlatformSpawned == false) {
            ItemStack enchantedRedWool = new ItemStack(Material.RED_WOOL);
            ItemMeta enchantedRedWoolMeta = enchantedRedWool.getItemMeta();
            enchantedRedWoolMeta.setDisplayName(ChatColor.RED + "Spawn Red Platform");
            enchantedRedWoolMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
            enchantedRedWoolMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS); 
            enchantedRedWool.setItemMeta(enchantedRedWoolMeta);
            return enchantedRedWool;
        } else {
            ItemStack greenGlassPane = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
            ItemMeta greenGlassPaneMeta = greenGlassPane.getItemMeta();
            greenGlassPaneMeta.setDisplayName(ChatColor.RED + "Red Platform Successfully Spawned");
            greenGlassPane.setItemMeta(greenGlassPaneMeta);
            return greenGlassPane;
        }
    }

    public static ItemStack getSetupSlot8(){
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

    public static ItemStack getSetupSlot10(){
        if(GameProperties.redPlatformLocation == null){
            ItemStack redGlassPane = new ItemStack(Material.RED_STAINED_GLASS_PANE);
            ItemMeta redGlassPaneMeta = redGlassPane.getItemMeta();
            redGlassPaneMeta.setDisplayName(ChatColor.RED + "Red Flag Location Not Set");
            redGlassPane.setItemMeta(redGlassPaneMeta);
            return redGlassPane;
        } else if(!GameProperties.redPlatformSpawned){
            ItemStack yellowGlassPane = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
            ItemMeta yellowGlassPaneMeta = yellowGlassPane.getItemMeta();
            yellowGlassPaneMeta.setDisplayName(ChatColor.RED + "Red Platform Not Spawned");
            yellowGlassPane.setItemMeta(yellowGlassPaneMeta);
            return yellowGlassPane;
        } else {
            ItemStack greenGlassPane = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
            ItemMeta greenGlassPaneMeta = greenGlassPane.getItemMeta();
            greenGlassPaneMeta.setDisplayName(ChatColor.RED + "Red Platform Successfully Spawned");
            greenGlassPane.setItemMeta(greenGlassPaneMeta);
            return greenGlassPane;
        }
    }

    public static ItemStack getSetupSlot9(){
        if(GameProperties.bluePlatformLocation == null){
            ItemStack redGlassPane = new ItemStack(Material.RED_STAINED_GLASS_PANE);
            ItemMeta redGlassPaneMeta = redGlassPane.getItemMeta();
            redGlassPaneMeta.setDisplayName(ChatColor.BLUE + "Blue Flag Location Not Set");
            redGlassPane.setItemMeta(redGlassPaneMeta);
            return redGlassPane;
        } else if(!GameProperties.bluePlatformSpawned){
            ItemStack yellowGlassPane = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
            ItemMeta yellowGlassPaneMeta = yellowGlassPane.getItemMeta();
            yellowGlassPaneMeta.setDisplayName(ChatColor.BLUE + "Blue Platform Not Spawned");
            yellowGlassPane.setItemMeta(yellowGlassPaneMeta);
            return yellowGlassPane;
        } else {
            ItemStack greenGlassPane = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
            ItemMeta greenGlassPaneMeta = greenGlassPane.getItemMeta();
            greenGlassPaneMeta.setDisplayName(ChatColor.BLUE + "Blue Platform Successfully Spawned");
            greenGlassPane.setItemMeta(greenGlassPaneMeta);
            return greenGlassPane;
        }
    }

    public static ItemStack getSetupSlot17(){
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

    public static ItemStack getTeamSlot1(Player p){
        if(Teams.getTeam(p).equals("Blue")){
            ItemStack blueChestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
            LeatherArmorMeta blueChestplateMeta = (LeatherArmorMeta) blueChestplate.getItemMeta();
            blueChestplateMeta.setDisplayName(ChatColor.BLUE + "You are on this team");
            blueChestplateMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true); 
            blueChestplateMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS); 
            blueChestplateMeta.setColor(Color.BLUE);
            blueChestplate.setItemMeta(blueChestplateMeta);
            return blueChestplate;
        } else {
            ItemStack blueChestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
            LeatherArmorMeta blueChestplateMeta = (LeatherArmorMeta) blueChestplate.getItemMeta();
            blueChestplateMeta.setDisplayName(ChatColor.BLUE + "Join Blue Team");
            blueChestplateMeta.setColor(Color.BLUE);
            blueChestplate.setItemMeta(blueChestplateMeta);
            return blueChestplate;
        }
    }

    public static ItemStack getTeamSlot2(Player p){
        if(Teams.getTeam(p).equals("Red")){
            ItemStack redChestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
            LeatherArmorMeta redChestplateMeta = (LeatherArmorMeta) redChestplate.getItemMeta();
            redChestplateMeta.setDisplayName(ChatColor.RED + "You are on this team");
            redChestplateMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true); 
            redChestplateMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS); 
            redChestplateMeta.setColor(Color.RED);
            redChestplate.setItemMeta(redChestplateMeta);
            return redChestplate;
        } else {
            ItemStack redChestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
            LeatherArmorMeta redChestplateMeta = (LeatherArmorMeta) redChestplate.getItemMeta();
            redChestplateMeta.setDisplayName(ChatColor.RED + "Join Red Team");
            redChestplateMeta.setColor(Color.RED);
            redChestplate.setItemMeta(redChestplateMeta);
            return redChestplate;
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

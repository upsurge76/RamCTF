package me.ramctf.InventorySlots;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import me.ramctf.Teams;
import net.md_5.bungee.api.ChatColor;

public class InventorySlotsTeam {
    
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

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

    public static ItemStack getPauseGameSlot(){
        ItemStack redstoneTorch = new ItemStack(Material.REDSTONE_TORCH);
        ItemMeta redstoneTorchMeta = redstoneTorch.getItemMeta();
        redstoneTorchMeta.setDisplayName(ChatColor.WHITE + "Pause Game");
        redstoneTorch.setItemMeta(redstoneTorchMeta);
        return redstoneTorch;
    }

    public static ItemStack getResumeGameSlot(){
        ItemStack redstoneLamp = new ItemStack(Material.REDSTONE_LAMP);
        ItemMeta redstoneLampMeta = redstoneLamp.getItemMeta();
        redstoneLampMeta.setDisplayName(ChatColor.WHITE + "Resume Game");
        redstoneLamp.setItemMeta(redstoneLampMeta);
        return redstoneLamp;
    }

    public static ItemStack getSkipPregameSlot(){
        ItemStack diamondSword = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta diamondSwordMeta = diamondSword.getItemMeta();
        diamondSwordMeta.setDisplayName(ChatColor.WHITE + "Skip Pregame");
        diamondSword.setItemMeta(diamondSwordMeta);
        return diamondSword;
    }
        
        
    public static ItemStack getGlassPane(){
        ItemStack glasspane = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta glasspaneMeta = glasspane.getItemMeta();
        glasspaneMeta.setDisplayName(" ");
        glasspane.setItemMeta(glasspaneMeta);
        return glasspane;
    }

}

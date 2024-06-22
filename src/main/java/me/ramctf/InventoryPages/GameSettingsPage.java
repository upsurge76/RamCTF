package me.ramctf.InventoryPages;
import org.bukkit.event.Listener;
import org.bukkit.Bukkit;

import org.bukkit.Material;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.ramctf.InventorySlots.InventorySlotsSettings;
import net.md_5.bungee.api.ChatColor;


public class GameSettingsPage implements Listener{
    static String inventoryName = "Game Settings";

    @EventHandler
    public void onPlayerClickInventory(InventoryClickEvent e){
        if(e.getView().getTitle().equals(inventoryName)){
            e.setCancelled(true);
            Player p = (Player) e.getWhoClicked();
            ItemStack clickedItem = e.getCurrentItem();
            if(clickedItem == null || clickedItem.getType() == Material.AIR){
                return;
            } 

            if(clickedItem.getType() == Material.ENDER_EYE){
                SetupPage.ShowHomePage(p);
            }

            if(clickedItem.getType() == Material.CLOCK){
                if(clickedItem.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Game Start Delay")){
                    PregameDelaySettingsPage.ShowHomePage(p);
                }
                if(clickedItem.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Game Duration")){
                    GameDurationSettingsPage.ShowHomePage(p);
                }
            }
        }
    }

    public static void ShowHomePage(Player p){
        Inventory inv = Bukkit.createInventory(p, 18, inventoryName);
        
        for(int i = 0; i < 18; i++){
            inv.setItem(i, InventorySlotsSettings.getGlassPane());
        }

        inv.setItem(0, InventorySlotsSettings.getSettingsBackSlot());
        inv.setItem(2, InventorySlotsSettings.getSettingsPregameTimeSlot());
        inv.setItem(3, InventorySlotsSettings.getSettingsGameTimeSlot());
        inv.setItem(11, InventorySlotsSettings.getSettingsPregameTimeInfoSlot());
        inv.setItem(12, InventorySlotsSettings.getSettingsGameTimeInfoSlot());
        p.openInventory(inv);
    }
}


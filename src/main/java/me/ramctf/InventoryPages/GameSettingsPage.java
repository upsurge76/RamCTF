package me.ramctf.InventoryPages;
import org.bukkit.event.Listener;
import org.bukkit.Bukkit;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.ramctf.GameProperties;
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
            } else if(clickedItem.getType() == Material.CLOCK){
                if(clickedItem.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Game Start Delay")){
                    PregameDelaySettingsPage.ShowHomePage(p);
                }
                if(clickedItem.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Game Duration")){
                    GameDurationSettingsPage.ShowHomePage(p);
                }
            } else if(clickedItem.getType() == Material.IRON_ORE){
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_COW_BELL, 1, 1);
                if(GameProperties.pregamePlayerLootDrop()){
                    GameProperties.setPregamePlayerLootDrop(false);
                } else {
                    GameProperties.setPregamePlayerLootDrop(true);
                }
                ShowHomePage(p);
            } else if(clickedItem.getType() == Material.GRASS_BLOCK){
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_COW_BELL, 1, 1);
                if(GameProperties.flagBuildProtection()){
                    GameProperties.setFlagBuildProtection(false);
                } else {
                    GameProperties.setFlagBuildProtection(true);
                }
                ShowHomePage(p);
            } else if(clickedItem.getType() == Material.COOKED_BEEF){
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_COW_BELL, 1, 1);
                if(GameProperties.doPregameHunger()){
                    GameProperties.setDoPregameHunger(false);
                } else {
                    GameProperties.setDoPregameHunger(true);
                }
                ShowHomePage(p);
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
        inv.setItem(4, InventorySlotsSettings.getSettingsPlayerLootDrop());
        inv.setItem(5, InventorySlotsSettings.getSettingsFlagBuildProtection());
        inv.setItem(6, InventorySlotsSettings.getSettingsHungerSlot());

        inv.setItem(11, InventorySlotsSettings.getSettingsPregameTimeInfoSlot());
        inv.setItem(12, InventorySlotsSettings.getSettingsGameTimeInfoSlot());
        inv.setItem(13, InventorySlotsSettings.getSettingsPlayerLootDropInfoSlot());
        inv.setItem(14, InventorySlotsSettings.getSettingsFlagBuildProtectionInfoSlot());
        inv.setItem(15, InventorySlotsSettings.getSettingsHungerInfoSlot());

        p.openInventory(inv);
    }
}



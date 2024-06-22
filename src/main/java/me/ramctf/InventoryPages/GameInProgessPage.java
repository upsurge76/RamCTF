package me.ramctf.InventoryPages;
import org.bukkit.event.Listener;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.ramctf.GameManager;
import me.ramctf.InventorySlots.InventorySlotsInGame;


public class GameInProgessPage implements Listener{
    static String inventoryName = "Game Options";

    @EventHandler
    public void onPlayerClickInventory(InventoryClickEvent e){
        if(e.getView().getTitle().equals(inventoryName)){
            e.setCancelled(true);
            Player p = (Player) e.getWhoClicked();
            ItemStack clickedItem = e.getCurrentItem();
            if(clickedItem == null || clickedItem.getType() == Material.AIR){
                return;
            }
            if(clickedItem.getType() == Material.REDSTONE_BLOCK){
                Bukkit.broadcastMessage(p.getName() + " has ended the game");
                GameManager.endGame();
            }
        }
    }

    public static void ShowHomePage(Player p){
        Inventory inv = Bukkit.createInventory(p, 9, inventoryName);
        for(int i = 0; i < 9; i++){
            inv.setItem(i, InventorySlotsInGame.getGlassPane());
        }

        inv.setItem(0, InventorySlotsInGame.getEndGameSlot());
        
        p.openInventory(inv);
    }
}



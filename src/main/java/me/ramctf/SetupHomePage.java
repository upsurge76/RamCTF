package me.ramctf;
import org.bukkit.event.Listener;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import net.md_5.bungee.api.ChatColor;

public class SetupHomePage implements Listener{
    static String inventoryName = "Game Setup";

    @EventHandler
    public void onPlayerClickInventory(InventoryClickEvent e){
        if(e.getView().getTitle().equals(inventoryName)){
            e.setCancelled(true);
            Player p = (Player) e.getWhoClicked();
            ItemStack clickedItem = e.getCurrentItem();
            if(clickedItem == null || clickedItem.getType() == Material.AIR){
                return;
            }
            if(clickedItem.getType() == Material.BLUE_BANNER){
                p.sendMessage(ChatColor.BLUE + "Blue Flag Location Set");
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1, 3);
                p.closeInventory();
                GameProperties.bluePlatformLocation = p.getLocation();
            
            } else if(clickedItem.getType() == Material.RED_BANNER){
                p.sendMessage(ChatColor.RED + "Red Flag Location Set");
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1, 3);
                p.closeInventory();
                GameProperties.redPlatformLocation = p.getLocation();

            } else if(clickedItem.getType() == Material.LEATHER_CHESTPLATE){
                if(!GameProperties.teamSetupStarted){
                    p.sendMessage(ChatColor.GREEN + "Team Setup Started");
                    Teams.initializeTeams();
                    GameProperties.teamSetupStarted = true;
                }
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_COW_BELL, 1, 3);
                p.closeInventory();
                TeamHomePage.ShowHomePage(p);
            }
        }
    }

    public static void ShowHomePage(Player p){
        Inventory inv = Bukkit.createInventory(p, 18, inventoryName);

        inv.setItem(0, InventorySlots.getSetupSlot1());
        inv.setItem(1, InventorySlots.getSetupSlot2());
        for(int i = 2; i < 8; i++){
            inv.setItem(i, InventorySlots.getGlassPane());
        }
        inv.setItem(8, InventorySlots.getSetupSlot8());
        inv.setItem(9, InventorySlots.getSetupSlot9());
        inv.setItem(10, InventorySlots.getSetupSlot10());
        for(int i = 11; i < 17; i++){
            inv.setItem(i, InventorySlots.getGlassPane());
        }
        inv.setItem(17, InventorySlots.getSetupSlot17());
        

        p.openInventory(inv);
    }
}



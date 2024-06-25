package me.ramctf.InventoryPages;
import org.bukkit.event.Listener;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.ramctf.GameManager;
import me.ramctf.GameProperties;
import me.ramctf.InventorySlots.InventorySlotsSetup;
import net.md_5.bungee.api.ChatColor;

public class SetupPage implements Listener{
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
                p.sendMessage(ChatColor.GREEN + "Blue Flag Location Set");
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1, 3);
                p.closeInventory();
                GameProperties.setBlueFlagLocationBase(new Location(p.getWorld(), p.getLocation().getBlockX()+.5, p.getLocation().getBlockY(), p.getLocation().getBlockZ()+.5));
            
            } else if(clickedItem.getType() == Material.RED_BANNER){
                p.sendMessage(ChatColor.GREEN + "Red Flag Location Set");
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1, 3);
                p.closeInventory();
                GameProperties.setRedFlagLocationBase(new Location(p.getWorld(), p.getLocation().getBlockX()+.5, p.getLocation().getBlockY(), p.getLocation().getBlockZ()+.5));

            } else if(clickedItem.getType() == Material.LEATHER_CHESTPLATE){
                if(!GameProperties.teamSetupStarted()){

                    for(Player player : Bukkit.getOnlinePlayers()){
                        player.sendMessage(ChatColor.GREEN + "Team Setup Started. Type /team to pick a team.");
                        if(!player.equals(p)){
                            TeamPage.ShowHomePage(player);
                        }
                    }
                    
                    GameProperties.setTeamSetupStarted(true);
                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_COW_BELL, 1, 3);
                    SetupPage.ShowHomePage(p);
                } 
            } else if(clickedItem.getType() == Material.COMPASS){
                GameSettingsPage.ShowHomePage(p);
            } else if(clickedItem.getType() == Material.LEVER){
                if(GameManager.isGameReadyToStart()){
                    p.sendMessage(ChatColor.GREEN + "Game Started");
                    p.closeInventory();
                    if(GameProperties.pregameTimer() > 0){
                        GameManager.startPregame();
                    } else {
                        GameManager.startGame();
                    }
                    

                } else {
                    p.sendMessage(ChatColor.RED + "Game not ready to start");
                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, 1);
                }
            }
        } 
    }

    public static void ShowHomePage(Player p){
        Inventory inv = Bukkit.createInventory(p, 18, inventoryName);
        for(int i = 0; i < 18; i++){
            inv.setItem(i, InventorySlotsSetup.getGlassPane());
        }

        inv.setItem(0, InventorySlotsSetup.getBlueFlagSlot());
        inv.setItem(1, InventorySlotsSetup.getRedFlagSlot());
        inv.setItem(4, InventorySlotsSetup.getStartButton());
        inv.setItem(8, InventorySlotsSetup.getTeamSetupSlot());
        inv.setItem(9, InventorySlotsSetup.getBlueFlagStatus());
        inv.setItem(10, InventorySlotsSetup.getRedFlagStatus());
        inv.setItem(13, InventorySlotsSetup.getSettingsSlot());
        inv.setItem(17, InventorySlotsSetup.getTeamSetupStatus());
        
        p.openInventory(inv);
    }
}



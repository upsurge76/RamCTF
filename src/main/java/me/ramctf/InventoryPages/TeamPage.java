package me.ramctf.InventoryPages;
import org.bukkit.event.Listener;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import me.ramctf.Teams;
import me.ramctf.InventorySlots.InventorySlotsTeam;
import net.md_5.bungee.api.ChatColor;

public class TeamPage implements Listener{
    static String inventoryName = "Team Setup";

    @EventHandler
    public void onPlayerClickInventory(InventoryClickEvent e){
        if(e.getView().getTitle().equals(inventoryName)){
            e.setCancelled(true);
            Player p = (Player) e.getWhoClicked();
            ItemStack clickedItem = e.getCurrentItem();
            if(clickedItem == null || clickedItem.getType() == Material.AIR){
                return;
            }
            
            if(clickedItem.getType() == Material.LEATHER_CHESTPLATE){
                LeatherArmorMeta meta = (LeatherArmorMeta) clickedItem.getItemMeta();

                if(meta.getColor().equals(Color.RED)){
                    Teams.addPlayerRedTeam(p);
                    p.sendMessage(ChatColor.RED + "You have joined the Red Team");
                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1, 3);
                    p.closeInventory();
                } else {
                    Teams.addPlayerBlueTeam(p);
                    p.sendMessage(ChatColor.BLUE + "You have joined the Blue Team");
                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1, 3);
                    p.closeInventory();
                }
            }
        }
    }

    public static void ShowHomePage(Player p){
        Inventory inv = Bukkit.createInventory(p, 9, inventoryName);

        inv.setItem(0, InventorySlotsTeam.getTeamSlot1(p));
        inv.setItem(1, InventorySlotsTeam.getTeamSlot2(p));
        for(int i = 2; i < 9; i++){
            inv.setItem(i, InventorySlotsTeam.getGlassPane());
        }

        p.openInventory(inv);
    }


}



package me.ramctf;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;


public class ArmorStealPreventing implements Listener{
    static String inventoryName = "Game Settings";

    @EventHandler
    public void onPlayerClickInventory(InventoryClickEvent e){
        if(e.getSlot() == 38){
            e.setCancelled(true);
        }
    }
}



package me.ramctf.EventHandler;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import me.ramctf.GameProperties;

public class AntiHunger implements Listener {
    @EventHandler
    public void onHungerDeplete(FoodLevelChangeEvent e) {
        if(!GameProperties.doPregameHunger()){
            e.setCancelled(true);
            e.setFoodLevel(20);   
        }
    }
}

package me.ramctf.EventHandler;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import me.ramctf.GameProperties;
import me.ramctf.Teams;
import net.md_5.bungee.api.ChatColor;

public class DeathHandler implements Listener{

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){
        Player p = e.getEntity().getPlayer();

        if(GameProperties.blueFlagCarrier != null){
            if(GameProperties.blueFlagCarrier.equals(p)){
                GameProperties.blueFlagCarrier = null;
                GameProperties.blueFlagCurrentLocation = p.getLocation();
                GameProperties.blueFlagOnGround = true;
                Teams.broadcastMessage(ChatColor.GREEN + p.getName() + " dropped your flag", "Red");
            }
        }
        if(GameProperties.redFlagCarrier != null){
            if(GameProperties.redFlagCarrier.equals(p)){
                GameProperties.redFlagCarrier = null;
                GameProperties.redFlagCurrentLocation = p.getLocation();
                GameProperties.redFlagOnGround = true;
                Teams.broadcastMessage(ChatColor.GREEN + p.getName() + " dropped your flag", "Blue");
            }
        } 
    }

}

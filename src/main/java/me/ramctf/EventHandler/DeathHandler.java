package me.ramctf.EventHandler;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import me.ramctf.FlagAutoRecoveryTimer;
import me.ramctf.GameProperties;
import me.ramctf.Teams;
import net.md_5.bungee.api.ChatColor;

public class DeathHandler implements Listener{

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){
        Player p = e.getEntity().getPlayer();

        if(GameProperties.blueFlagCarrier() != null){
            if(GameProperties.blueFlagCarrier().equals(p)){
                GameProperties.setBlueFlagCarrier(null);
                GameProperties.setBlueFlagCurrentLocation(p.getLocation().getBlock().getLocation());
                GameProperties.setBlueFlagOnGround(true);
                Teams.broadcastMessage(ChatColor.GREEN + p.getName() + " dropped your flag", "Red", p);
                FlagAutoRecoveryTimer.startFlagTimer("Blue");
            }
        }
        if(GameProperties.redFlagCarrier() != null){
            if(GameProperties.redFlagCarrier().equals(p)){
                GameProperties.setRedFlagCarrier(null);
                GameProperties.setRedFlagCurrentLocation(p.getLocation().getBlock().getLocation());
                GameProperties.setRedFlagOnGround(true);
                Teams.broadcastMessage(ChatColor.GREEN + p.getName() + " dropped your flag", "Blue", p); 
                FlagAutoRecoveryTimer.startFlagTimer("Red");
            }
        } 
    }

}

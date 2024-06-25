package me.ramctf;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class FlagProximityWarning {

    private static Set<Player> playersInRange = new HashSet<>();
    
    public static void calculateFlagPlayerProximity(){
        for(Player p: Bukkit.getOnlinePlayers()){
            if(!p.getGameMode().equals(GameMode.SPECTATOR)){
                if(Teams.getTeam(p).equals("Red")){
                    if(Helpers.withinDistance(p.getLocation(), GameProperties.blueFlagLocationBase(), GameProperties.flagProximityDistance())){
                        if(!playersInRange.contains(p)){
                            p.sendTitle(ChatColor.RED + "You are close to enemy flag", "You will drop items if killed", 10, 70, 10);
                            Helpers.playAlarm(p);
                            playersInRange.add(p);
                        }
                    } else {
                        playersInRange.remove(p);
                    }
                } else if(Teams.getTeam(p).equals("Blue")){
                    if(Helpers.withinDistance(p.getLocation(), GameProperties.redFlagLocationBase(), GameProperties.flagProximityDistance())){
                        if(!playersInRange.contains(p)){
                            Helpers.playAlarm(p);
                            p.sendTitle(ChatColor.RED + "You are close to enemy flag", "You will drop items if killed", 10, 70, 10);
                            playersInRange.add(p);
                        }
                    } else {
                        playersInRange.remove(p);
                    }
                }
            }
        }
    }
}

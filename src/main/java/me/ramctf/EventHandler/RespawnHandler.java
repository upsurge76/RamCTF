package me.ramctf.EventHandler;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.ramctf.GameProperties;
import me.ramctf.Helpers;
import me.ramctf.RamCTF;
import me.ramctf.Teams;

public class RespawnHandler implements Listener{

    public int getTimer(Player p){
        String team = Teams.getTeam(p);
        int countdown = 5;

        if(team.equals("Red")){
            if(Helpers.withinDistance(p.getLocation(), GameProperties.redFlagLocationBase(), GameProperties.flagProximityDistance())){
                countdown = 8;
            }
        }
        if(team.equals("Blue")){
            if(Helpers.withinDistance(p.getLocation(), GameProperties.redFlagLocationBase(), GameProperties.flagProximityDistance())){
                countdown = 8;
            }
        }

        return countdown;
    }
    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e){
        Player p = e.getPlayer();
        
        if((GameProperties.mainGameRunning() || GameProperties.pregameRunning()) && GameProperties.blueFlagLocationBase() != null && GameProperties.redFlagLocationBase() != null){
        
            p.setGameMode(GameMode.SPECTATOR);

            if(Teams.getTeam(p).equals("Blue")){

                new BukkitRunnable() {
                int countDown = getTimer(p);
                int timer = countDown;
                
                @Override
                public void run() {
                    if (timer == countDown){p.teleport(GameProperties.blueFlagLocationBase().clone().add(0, 1, 0.5));}
                    if (timer > 0) {
                        p.sendTitle(Integer.toString(timer), "", 0, 21, 0);
                        timer -= 1;
                    } else {
                        p.setGameMode(GameMode.SURVIVAL);
                        p.teleport(GameProperties.blueFlagLocationBase().clone().add(0, 1, 0.5));
                        cancel();
                    }
                }
                }.runTaskTimer(JavaPlugin.getPlugin(RamCTF.class), 0, 20);

            } else if(Teams.getTeam(p).equals("Red")){

                new BukkitRunnable() {
                    int countDown = getTimer(p);
                    int timer = countDown;

                    @Override
                    public void run() {
                        if (timer == countDown){p.teleport(GameProperties.redFlagLocationBase().clone().add(0, 1, 0.5));}
                        if (timer > 0) {
                            p.sendTitle(Integer.toString(timer), "", 0, 21, 0);
                            timer -= 1;
                        } else {
                            p.setGameMode(GameMode.SURVIVAL);
                            p.teleport(GameProperties.redFlagLocationBase().clone().add(0, 1, 0.5));
                            cancel();
                        }
                    }
                    }.runTaskTimer(JavaPlugin.getPlugin(RamCTF.class), 0, 20);
            }
        }
    }

}

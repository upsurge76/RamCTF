package me.ramctf.EventHandler;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.ramctf.GameProperties;
import me.ramctf.RamCTF;
import me.ramctf.Teams;

public class RespawnHandler implements Listener{

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e){
        Player p = e.getPlayer();
        p.setGameMode(GameMode.SPECTATOR);

        if(Teams.getTeam(p).equals("Blue")){

            new BukkitRunnable() {
            int timer = 5;
            @Override
            public void run() {
                if (timer == 5){p.teleport(GameProperties.blueFlagLocationBase().clone().add(0, 1, 0.5));}
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
                int timer = 5;
                @Override
                public void run() {
                    if (timer == 5){p.teleport(GameProperties.redFlagLocationBase().clone().add(0, 1, 0.5));}
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

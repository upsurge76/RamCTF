package me.ramctf;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Runnable {

    public static void runContinuously() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if(GameProperties.gameStarted){
                    Teams.updateScoreboard();
                } else {
                    if(GameProperties.blueFlagLocation != null){
                        DustOptions dustOptions = new DustOptions(Color.fromRGB(0, 0, 255), 1.0F);

                        for (int y = 0; y < 2; y++) { 
                                Location particleLocation = GameProperties.blueFlagLocation.clone().add(0, y, 0);
                                particleLocation.getWorld().spawnParticle(Particle.REDSTONE, particleLocation, 10, 0.2, 0.2, 0.2, dustOptions);
                            }
                        }
                    if(GameProperties.redFlagLocation != null){
                        DustOptions dustOptions = new DustOptions(Color.fromRGB(255, 0, 0), 1.0F);
                        
                        for (int y = 0; y < 2; y++) { 
                            Location particleLocation = GameProperties.redFlagLocation.clone().add(0, y, 0);
                            particleLocation.getWorld().spawnParticle(Particle.REDSTONE, particleLocation, 10, 0.2, 0.2, 0.2, dustOptions);
                        }
                    }
                }
            }
        }.runTaskTimer(JavaPlugin.getPlugin(RamCTF.class), 0, 5);
    }

}
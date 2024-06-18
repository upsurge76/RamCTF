package me.ramctf;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Runnable {

    public static void updateScoreboard() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if(GameProperties.gameStarted){
                    Teams.updateScoreboard();
                }
            }
        }.runTaskTimer(JavaPlugin.getPlugin(RamCTF.class), 0, 10);
    }

}
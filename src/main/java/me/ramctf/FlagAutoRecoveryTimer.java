package me.ramctf;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.ChatColor;

public class FlagAutoRecoveryTimer {
    public static void startFlagTimer(String flag){
        if(flag.equals("Blue")){
            BossBar bossBar = Bukkit.createBossBar(ChatColor.BLUE + "Blue Flag Will Be Automatically Recovered in " + GameProperties.flagAutorecoveryTimer() + " seconds", BarColor.BLUE, BarStyle.SOLID);
            bossBar.setProgress(1);
            for(Player p : Bukkit.getOnlinePlayers()){
                bossBar.addPlayer(p);
            }
            new BukkitRunnable() {
            int timer = GameProperties.flagAutorecoveryTimer();
            @Override
            public void run() {
                if(GameProperties.blueFlagLocationBase().equals(GameProperties.blueFlagCurrentLocation())){
                    bossBar.removeAll();
                    cancel();
                }
                if(timer == 0){
                    bossBar.removeAll();
                    FlagLogic.removeRedFlag(GameProperties.redFlagCurrentLocation());
                    GameProperties.setRedFlagCurrentLocation(GameProperties.redFlagLocationBase());
                    Teams.broadcastMessage(ChatColor.GREEN + "Your flag has been recovered", "Blue");
                    cancel();
                }
                bossBar.setProgress(timer / (double)GameProperties.flagAutorecoveryTimer());
                timer -= 1;
            }
            }.runTaskTimer(JavaPlugin.getPlugin(RamCTF.class), 0, 20);

        } else if(flag.equals("Red")){
            BossBar bossBar = Bukkit.createBossBar(ChatColor.RED + "Red Flag Will Be Automatically Recovered in " + GameProperties.flagAutorecoveryTimer() + " seconds", BarColor.RED, BarStyle.SOLID);
            bossBar.setProgress(1);
            for(Player p : Bukkit.getOnlinePlayers()){
                bossBar.addPlayer(p);
            }
            new BukkitRunnable() {
            int timer = GameProperties.flagAutorecoveryTimer();
            @Override
            public void run() {
                if(GameProperties.redFlagLocationBase().equals(GameProperties.redFlagCurrentLocation())){
                    bossBar.removeAll();
                    cancel();
                }
                if(timer == 0){
                    FlagLogic.removeRedFlag(GameProperties.redFlagCurrentLocation());
                    GameProperties.setRedFlagCurrentLocation(GameProperties.redFlagLocationBase());
                    Teams.broadcastMessage(ChatColor.GREEN + "Your flag has been recovered", "Red");
                    cancel();
                }
                bossBar.setProgress(timer / (double)GameProperties.flagAutorecoveryTimer());
                timer -= 1;
            }
            }.runTaskTimer(JavaPlugin.getPlugin(RamCTF.class), 0, 20);
        }
    }
}

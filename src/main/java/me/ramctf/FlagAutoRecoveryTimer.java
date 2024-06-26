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
    public static BossBar redBossBar = Bukkit.createBossBar(ChatColor.RED + "Red Flag Will Be Automatically Recovered in " + GameProperties.flagAutorecoveryTimer() + " seconds", BarColor.RED, BarStyle.SOLID);
    public static BossBar blueBossBar = Bukkit.createBossBar(ChatColor.BLUE + "Blue Flag Will Be Automatically Recovered in " + GameProperties.flagAutorecoveryTimer() + " seconds", BarColor.BLUE, BarStyle.SOLID);
    
    
    public static void startFlagTimer(String flag){

        if(flag.equals("Blue")){
            blueBossBar.setProgress(1);
            for(Player p : Bukkit.getOnlinePlayers()){
                blueBossBar.addPlayer(p);
            }
            new BukkitRunnable() {
            int timer = GameProperties.flagAutorecoveryTimer();
            @Override
            public void run() {
                blueBossBar.setProgress(timer / (double)GameProperties.flagAutorecoveryTimer());
                timer -= 1;
                if(GameProperties.blueFlagLocationBase().equals(GameProperties.blueFlagCurrentLocation())){
                    blueBossBar.removeAll();
                    cancel();
                }
                if(timer == 0){
                    blueBossBar.removeAll();
                    FlagLogic.removeBlueFlag(GameProperties.blueFlagCurrentLocation());
                    GameProperties.setBlueFlagCurrentLocation(GameProperties.blueFlagLocationBase());
                    Teams.broadcastMessage(ChatColor.RED + "Enemy flag has timed out", "Red");
                    Teams.broadcastMessage(ChatColor.GREEN + "Your flag has been recovered", "Blue");
                }
            }
            }.runTaskTimer(JavaPlugin.getPlugin(RamCTF.class), 0, 20);

        } else if(flag.equals("Red")){
            redBossBar.setProgress(1);
            for(Player p : Bukkit.getOnlinePlayers()){
                redBossBar.addPlayer(p);
            }
            new BukkitRunnable() {
            int timer = GameProperties.flagAutorecoveryTimer();
            @Override
            public void run() {
                redBossBar.setProgress(timer / (double)GameProperties.flagAutorecoveryTimer());
                timer -= 1;
                if(GameProperties.redFlagLocationBase().equals(GameProperties.redFlagCurrentLocation())){
                    redBossBar.removeAll();
                    cancel();
                }
                if(timer == 0){
                    redBossBar.removeAll();
                    FlagLogic.removeRedFlag(GameProperties.redFlagCurrentLocation());
                    GameProperties.setRedFlagCurrentLocation(GameProperties.redFlagLocationBase());
                    Teams.broadcastMessage(ChatColor.RED + "Enemy flag has timed out", "Blue");
                    Teams.broadcastMessage(ChatColor.GREEN + "Your flag has been recovered", "Red");
                    cancel();
                }
            }
            }.runTaskTimer(JavaPlugin.getPlugin(RamCTF.class), 0, 20);
        }
    }
}

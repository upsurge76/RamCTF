package me.ramctf;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.GameRule;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class GameManager {
    public static boolean isGameReadyToStart() {
        return GameProperties.teamSetupCompleted() && GameProperties.blueFlagLocationBase() != null && GameProperties.redFlagLocationBase() != null;
    }

    public static void startPregame() {
        
        Bukkit.getServer().getWorld("world").setGameRule(GameRule.KEEP_INVENTORY, true);

        GameProperties.setPregameStarted(true);
        GameProperties.setBlueFlagOnGround(true);
        GameProperties.setRedFlagOnGround(true);
        GameProperties.setBlueFlagCurrentLocation(GameProperties.blueFlagLocationBase());
        GameProperties.setRedFlagCurrentLocation(GameProperties.redFlagLocationBase());
        
        Runnable.teleportCountDownDelay();
    }

    public static void startGame(){
        GameProperties.setGameStarted(true);
        GameProperties.setPregameStarted(false);

        GameProperties.setBlueFlagOnGround(true);
        GameProperties.setRedFlagOnGround(true);
        GameProperties.setBlueFlagCurrentLocation(GameProperties.blueFlagLocationBase());
        GameProperties.setRedFlagCurrentLocation(GameProperties.redFlagLocationBase());

        GameProperties.setMinutesUntilGameStart(0);
        GameProperties.setMinutesUntilGameEnd(GameProperties.gameTimer());

        Runnable.gameTimer(0, GameProperties.gameTimer());

    }
    
    public static void endGame(){
        if(GameProperties.blueTeamScore() > GameProperties.redTeamScore()){
            Bukkit.broadcastMessage(ChatColor.GREEN + "Blue Team Wins!");

        } else if(GameProperties.redTeamScore() > GameProperties.blueTeamScore()){
            Bukkit.broadcastMessage(ChatColor.GREEN + "Red Team Wins!");
            
        } else {
            Bukkit.broadcastMessage(ChatColor.GREEN + "Tie Game!");
        }
        GameProperties.resetAllGameProperties();
        
    }
}

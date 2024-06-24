package me.ramctf;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.FireworkEffect;
import org.bukkit.GameRule;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;


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
            spawnFirework(GameProperties.blueFlagLocationBase());

        } else if(GameProperties.redTeamScore() > GameProperties.blueTeamScore()){
            Bukkit.broadcastMessage(ChatColor.GREEN + "Red Team Wins!");
            spawnFirework(GameProperties.redFlagLocationBase());
            
        } else {
            Bukkit.broadcastMessage(ChatColor.GREEN + "Tie Game!");
        }
        GameProperties.resetAllGameProperties();

    }

    private static void spawnFirework(Location location) {
        World world = location.getWorld();
        Firework firework = world.spawn(location, Firework.class);
        FireworkMeta fireworkMeta = firework.getFireworkMeta();

        // Customize the firework effect as desired
        fireworkMeta.addEffect(FireworkEffect.builder()
                .withColor(org.bukkit.Color.BLUE)
                .with(FireworkEffect.Type.BALL)
                .withFlicker()
                .withTrail()
                .build());
        fireworkMeta.setPower(1);

        firework.setFireworkMeta(fireworkMeta);
    }
}

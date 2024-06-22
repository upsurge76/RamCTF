package me.ramctf;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import org.bukkit.ChatColor;

public class Runnable {

    private static int pregameTicksElapsed = 1;
    private static int gameTicksElapsed = 1;
    private static boolean pregameAlertPlayed = false;

    public static void runEvery2Ticks() {


        new BukkitRunnable() {
            @Override
            public void run() {

                Teams.updateScoreboard();

                for(Player p : Bukkit.getOnlinePlayers()){
                    if(GameProperties.blueFlagCarrier() != null && GameProperties.blueFlagCarrier().equals(p)){
                        DustOptions dustOptions = new DustOptions(Color.fromRGB(0, 0, 255), 1.0F);
                        p.getWorld().spawnParticle(Particle.REDSTONE,  p.getLocation(), 45, 1, 0, 1, dustOptions);
                    } 
                    if(GameProperties.redFlagCarrier() != null && GameProperties.redFlagCarrier().equals(p)){
                        DustOptions dustOptions = new DustOptions(Color.fromRGB(255, 0, 0), 1.0F);
                        p.getWorld().spawnParticle(Particle.REDSTONE,  p.getLocation(), 45, 1, 0, 1, dustOptions);
                    }
                }

                if(GameProperties.blueFlagLocationBase() != null && (!GameProperties.blueFlagLocationBase().equals(GameProperties.blueFlagCurrentLocation()))){
                    DustOptions dustOptions = new DustOptions(Color.fromRGB(0, 0, 255), 1.0F);
                    Location particleLocation = GameProperties.blueFlagLocationBase().clone().add(0, 1, 0);
                    particleLocation.getWorld().spawnParticle(Particle.REDSTONE, particleLocation, 25, 0.2, 0.4, 0.2, dustOptions);

                }
                if(GameProperties.redFlagLocationBase() != null && (!GameProperties.redFlagLocationBase().equals(GameProperties.redFlagCurrentLocation()))){
                    DustOptions dustOptions = new DustOptions(Color.fromRGB(255, 0, 0), 1.0F);
                    Location particleLocation = GameProperties.redFlagLocationBase().clone().add(0, 1, 0);
                    particleLocation.getWorld().spawnParticle(Particle.REDSTONE, particleLocation, 25, 0.2, 0.4, 0.2, dustOptions);
                }
                if(GameProperties.blueFlagOnGround() && (!GameProperties.blueFlagLocationBase().equals(GameProperties.blueFlagCurrentLocation()))){
                    for(int i = 5; i < 100; i+=1){
                        GameProperties.blueFlagCurrentLocation().getWorld().spawnParticle(Particle.SMOKE_LARGE, GameProperties.blueFlagCurrentLocation().clone().add(.5, i, .5), 2, 0, 0.3, 0, 0);
                    }
                }
                if(GameProperties.redFlagOnGround() && (!GameProperties.redFlagLocationBase().equals(GameProperties.redFlagCurrentLocation()))){
                    for(int i = 5; i < 100; i+=1){
                        GameProperties.redFlagCurrentLocation().getWorld().spawnParticle(Particle.SMOKE_LARGE, GameProperties.redFlagCurrentLocation().clone().add(.5, i, .5), 2, 0, 0.3, 0, 0);
                    }
                }
                if(GameProperties.blueFlagOnGround()){
                    FlagLogic.spawnBlueFlag(GameProperties.blueFlagCurrentLocation());
                }
                
                if(GameProperties.redFlagOnGround()){
                    FlagLogic.spawnRedFlag(GameProperties.redFlagCurrentLocation());
                }
            
            }
        }.runTaskTimer(JavaPlugin.getPlugin(RamCTF.class), 0, 2);
    }

    public static void teleportCountDownDelay(){

        for(Player p : Bukkit.getOnlinePlayers()){
            p.playSound(p.getLocation(), org.bukkit.Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
            p.sendTitle(ChatColor.GREEN + "", "You will be teleported to your flag in 3 seconds", 0, 60, 5);
        }
        
        new BukkitRunnable() {

            @Override
            public void run() {
                for(Player p : Bukkit.getOnlinePlayers()){
                    String team = Teams.getTeam(p);
                    if(team.equalsIgnoreCase("Blue")){
                        p.teleport(GameProperties.blueFlagLocationBase().clone().add(0, 1, 0.5));
                    } else if(team.equalsIgnoreCase("Red")){
                        p.teleport(GameProperties.redFlagLocationBase().clone().add(0, 1, 0.5));
                    }
                }
                cancel();
                displayGameStartMessage();
                pregameTicksElapsed = 0;
                }

            }.runTaskTimer(JavaPlugin.getPlugin(RamCTF.class), 60, 1);

    }

    private static void displayGameStartMessage() {
        for(Player p : Bukkit.getOnlinePlayers()){
            if(GameProperties.pregameTimer() > 0){
                p.sendTitle(ChatColor.GREEN + "", "You have " + String.valueOf(GameProperties.pregameTimer()) + " minutes to collect items before the game begins", 5, 60, 5);
                p.playSound(p.getLocation(), org.bukkit.Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);  
                }
            }
        pregameTimer();
    }

    private static void pregameTimer() {
        GameProperties.setMinutesUntilGameStart(GameProperties.pregameTimer());
        new BukkitRunnable() {
            @Override
            public void run() {
                pregameTicksElapsed++;
                if(!GameProperties.pregameStarted()){
                    cancel(); //in case game is ended early
                }
                if(GameProperties.minutesUntilGameStart() == 0){
                    GameProperties.setPregameStarted(false);
                    GameProperties.setGameStarted(true);
                    pregameAlertPlayed = false;
                    GameManager.startGame();
                    cancel();

                }
                if(pregameTicksElapsed % (20*60) == 0){
                    GameProperties.setMinutesUntilGameStart(GameProperties.minutesUntilGameStart() - 1);
                }
                if(GameProperties.minutesUntilGameStart() == 1 && !pregameAlertPlayed){
                    for(Player p : Bukkit.getOnlinePlayers()){
                        p.sendTitle(ChatColor.GREEN + "", "Game starting in 1 minute", 5, 60, 5);
                        p.playSound(p.getLocation(), org.bukkit.Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
                        pregameAlertPlayed = true;
                    }
                }
            }
        }.runTaskTimer(JavaPlugin.getPlugin(RamCTF.class), 0, 1);
    }

    public static void gameTimer(int delay, int durationMins){
        new BukkitRunnable() {
            @Override
            public void run() {
                FlagLogic.calculateCaptures();

                gameTicksElapsed++;
                if(!GameProperties.gameStarted()){
                    cancel(); //in case game is ended early
                }
                if(GameProperties.minutesUntilGameEnd() == 0){
                    cancel(); 
                }
                if(gameTicksElapsed % (20*60) == 0){
                    GameProperties.setMinutesUntilGameEnd(GameProperties.minutesUntilGameEnd() - 1);
                }
            }
        }.runTaskTimer(JavaPlugin.getPlugin(RamCTF.class), delay, 1);
    }

}
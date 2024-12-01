package me.ramctf;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.potion.PotionEffectType;


public class GameManager {
    public static boolean isGameReadyToStart() {
        return GameProperties.teamSetupCompleted() && GameProperties.blueFlagLocationBase() != null && GameProperties.redFlagLocationBase() != null;
    }

    public static void startPregame() {

        GameProperties.setBlueFlagOnGround(true);
        GameProperties.setRedFlagOnGround(true);
        GameProperties.setBlueFlagCurrentLocation(GameProperties.blueFlagLocationBase());
        GameProperties.setRedFlagCurrentLocation(GameProperties.redFlagLocationBase());
        Runnable.teleportCountDownDelay();
    }

    public static void startGame(){
        GameProperties.setBlueFlagOnGround(true);
        GameProperties.setRedFlagOnGround(true);
        GameProperties.setBlueFlagCurrentLocation(GameProperties.blueFlagLocationBase());
        GameProperties.setRedFlagCurrentLocation(GameProperties.redFlagLocationBase());

        for(Player p : Bukkit.getOnlinePlayers()){
            p.sendTitle(ChatColor.GREEN + "Game Started", "", 10, 60, 20);
            p.playSound(p.getLocation(), org.bukkit.Sound.BLOCK_NOTE_BLOCK_COW_BELL, 1, 1);
            p.setHealth(20);
            p.setFoodLevel(20);
            if(Teams.getTeam(p).equals("Red")){
                p.teleport(GameProperties.redFlagLocationBase().clone().add(0,.5,.5));
            } else if(Teams.getTeam(p).equals("Blue")){
                p.teleport(GameProperties.blueFlagLocationBase().clone().add(0,.5,.5));
            } 
        }

        GameProperties.setMainGameRunning(true);
        GameProperties.setPregameRunning(false);

        GameProperties.setMinutesUntilGameEnd(GameProperties.gameTimer());
        Runnable.gameTimer(0, GameProperties.gameTimer());

    }

    public static void pauseGame(){
        GameProperties.setGamePaused(true);
        if (GameProperties.pregameRunning()) {
            Bukkit.broadcastMessage(ChatColor.GREEN + "Pregame Paused");
        } else {
            Bukkit.broadcastMessage(ChatColor.GREEN + "Game Paused");
        }
    }

    public static void resumeGame(){
        GameProperties.setGamePaused(false);
        if (GameProperties.pregameRunning()) {
            Bukkit.broadcastMessage(ChatColor.GREEN + "Pregame Resumed");
        } else {
            Bukkit.broadcastMessage(ChatColor.GREEN + "Game Resumed");
        }
    }
    
    public static void endGame(){
        for(Player p : Bukkit.getOnlinePlayers()){
            p.removePotionEffect(PotionEffectType.GLOWING);
        }

        GameProperties.setBlueFlagCarrier(null);
        GameProperties.setRedFlagCarrier(null);

        FlagLogic.removeBlueFlag(GameProperties.blueFlagCurrentLocation());
        FlagLogic.removeRedFlag(GameProperties.redFlagCurrentLocation());
        
        GameProperties.setRedFlagCurrentLocation(null);
        GameProperties.setBlueFlagCurrentLocation(null);

        GameProperties.setRedFlagOnGround(false);
        GameProperties.setBlueFlagOnGround(false);

        GameProperties.setMainGameRunning(false);
        GameProperties.setPregameRunning(false);
        GameProperties.setGamePaused(false);

        if(GameProperties.blueTeamScore() > GameProperties.redTeamScore()){
            Bukkit.broadcastMessage(ChatColor.GREEN + "Blue Team Wins!");
            spawnFirework(GameProperties.blueFlagLocationBase());

        } else if(GameProperties.redTeamScore() > GameProperties.blueTeamScore()){
            Bukkit.broadcastMessage(ChatColor.GREEN + "Red Team Wins!");
            spawnFirework(GameProperties.redFlagLocationBase());
            
        } else {
            Bukkit.broadcastMessage(ChatColor.GREEN + "Tie Game!");
        }

        if(GameProperties.clearInventoryAfterGameEnd()){
            for(Player p : Bukkit.getOnlinePlayers()){
                p.getInventory().clear();
            }
        }

        if(GameProperties.teleportAfterGameEnd()){
            for(Player p : Bukkit.getOnlinePlayers()){
                if(Teams.getTeam(p).equals("Red")){
                    p.teleport(GameProperties.redFlagLocationBase().clone().add(0,.5,.5));
                } else if(Teams.getTeam(p).equals("Blue")){
                    p.teleport(GameProperties.blueFlagLocationBase().clone().add(0,.5,.5));
                }
            }
        }

       
    }

    private static void spawnFirework(Location location) {
        World world = location.getWorld();
        Firework firework = world.spawn(location, Firework.class);
        FireworkMeta fireworkMeta = firework.getFireworkMeta();

        fireworkMeta.addEffect(FireworkEffect.builder()
                .withColor(org.bukkit.Color.WHITE)
                .with(FireworkEffect.Type.BALL)
                .withFlicker()
                .withTrail()
                .build());
        fireworkMeta.setPower(1);

        firework.setFireworkMeta(fireworkMeta);
    }
}

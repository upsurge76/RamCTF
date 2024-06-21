package me.ramctf;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import net.md_5.bungee.api.ChatColor;

public class FlagLogic implements Listener{
    
    private static int captureDistance = 1;

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Block b = e.getBlock();
        int x, z;

        if(GameProperties.blueFlagLocationBase != null){
            x = Helpers.get3AxisDistance(b.getLocation(), GameProperties.blueFlagLocationBase)[0];
            z = Helpers.get3AxisDistance(b.getLocation(), GameProperties.blueFlagLocationBase)[2];

            if(x < 3 && z < 3){
                e.setCancelled(true);
            }
        }

        if(GameProperties.redFlagLocationBase != null){
            x = Helpers.get3AxisDistance(b.getLocation(), GameProperties.redFlagLocationBase)[0];
            z = Helpers.get3AxisDistance(b.getLocation(), GameProperties.redFlagLocationBase)[2];

            if(x < 3 && z < 3){
                e.setCancelled(true);
            }
        }        
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){
        Block b = e.getBlock();
        
        int x, z;

        if(GameProperties.blueFlagLocationBase != null){
            x = Helpers.get3AxisDistance(b.getLocation(), GameProperties.blueFlagLocationBase)[0];
            z = Helpers.get3AxisDistance(b.getLocation(), GameProperties.blueFlagLocationBase)[2];
    
            if(x < 3 && z < 3){
                e.setCancelled(true);
            }
        }

        if(GameProperties.redFlagLocationBase != null){
            x = Helpers.get3AxisDistance(b.getLocation(), GameProperties.redFlagLocationBase)[0];
            z = Helpers.get3AxisDistance(b.getLocation(), GameProperties.redFlagLocationBase)[2];
    
            if(x < 3 && z < 3){
                e.setCancelled(true);
            }
        }

    }

    public static void calculateCaptures(){
        for(Player p: Bukkit.getOnlinePlayers()){
            if(playerCanStealThisFlag(p, "Red")){
                playerStoleRedFlagTasks(p);
            }
            if(playerCanStealThisFlag(p, "Blue")){
                playerStoleBlueFlagTasks(p);
            }
            if(playerCanRecoverThisFlag(p, "Red")){
                playerRecoveredRedFlagTasks(p);
            }
            if(playerCanRecoverThisFlag(p, "Blue")){
                playerRecoveredBlueFlagTasks(p);
            }
            if(playerCanCaptureThisFlag(p, "Red")){
                playerCapturedRedFlagTasks(p);
            }
            if(playerCanCaptureThisFlag(p, "Blue")){
                playerCapturedBlueFlagTasks(p);
            }

        }
    }

    public static void spawnBlueFlag(Location loc){
        loc.getBlock().setType(org.bukkit.Material.BLUE_BANNER);
    }

    public static void spawnRedFlag(Location loc){
        loc.getBlock().setType(org.bukkit.Material.RED_BANNER);
    }

    public static void removeBlueFlag(Location loc){
        loc.getBlock().setType(org.bukkit.Material.AIR);
    }

    public static void removeRedFlag(Location loc){
        loc.getBlock().setType(org.bukkit.Material.AIR);
    }

    public static boolean playerCanRecoverThisFlag(Player p, String team){

        if(team.equalsIgnoreCase("Blue")){
            if(Teams.getTeam(p).equalsIgnoreCase("Blue")){
                if(GameProperties.redFlagOnGround){
                    if(Helpers.withinDistance(p.getLocation(), GameProperties.redFlagCurrentLocation, captureDistance)){
                        return true;
                    }
                }
            }
        }
         if(team.equalsIgnoreCase("Red")){
            if(Teams.getTeam(p).equalsIgnoreCase("Red")){
                if(GameProperties.blueFlagOnGround){
                    if(Helpers.withinDistance(p.getLocation(), GameProperties.blueFlagCurrentLocation, captureDistance)){
                        return true;
                    }
                }
            }
         }

        return false;
    }

    

    public static boolean playerCanStealThisFlag(Player p, String flag){

        if(flag.equalsIgnoreCase("Blue")){
            if(Teams.getTeam(p).equalsIgnoreCase("Red")){
                if(GameProperties.blueFlagOnGround){
                    if(Helpers.withinDistance(p.getLocation(), GameProperties.blueFlagCurrentLocation, captureDistance)){
                        return true;
                    }
                }
            }
        }
            
        if(flag.equalsIgnoreCase("Red")){
            if(Teams.getTeam(p).equalsIgnoreCase("Blue")){
                if(GameProperties.redFlagOnGround){
                    if(Helpers.withinDistance(p.getLocation(), GameProperties.redFlagCurrentLocation, captureDistance)){
                        return true;
                    }
                }
            }
        }

        return false;
        
    }

    private static boolean playerCanCaptureThisFlag(Player p, String flag){

        if(flag.equalsIgnoreCase("Blue")){
            if(Teams.getTeam(p).equalsIgnoreCase("Red")){
                if(p.equals(GameProperties.blueFlagCarrier)){
                    if(Helpers.withinDistance(p.getLocation(), GameProperties.redFlagLocationBase, captureDistance)){
                        return true;
                    }
                }
            }
        } 
        if(flag.equalsIgnoreCase("Red")){
            if(Teams.getTeam(p).equalsIgnoreCase("Blue")){
                if(p.equals(GameProperties.redFlagCarrier)){
                    if(Helpers.withinDistance(p.getLocation(), GameProperties.blueFlagLocationBase, captureDistance)){
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private static void playerRecoveredRedFlagTasks(Player p){
        removeRedFlag(GameProperties.redFlagCurrentLocation);
        GameProperties.redFlagCurrentLocation = GameProperties.redFlagLocationBase;
        p.playSound(p.getLocation(), org.bukkit.Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 1, 1);
        for(Player player : Bukkit.getOnlinePlayers()){
            player.sendMessage(ChatColor.GREEN + "Red Flag Returned");
        }
    }

    private static void playerRecoveredBlueFlagTasks(Player p){
        removeBlueFlag(GameProperties.blueFlagCurrentLocation);
        GameProperties.blueFlagCurrentLocation = GameProperties.blueFlagLocationBase;
        p.playSound(p.getLocation(), org.bukkit.Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 1, 1);
        for(Player player : Bukkit.getOnlinePlayers()){
            player.sendMessage(ChatColor.GREEN + "Blue Flag Returned");
        }
    }

    private static void playerStoleRedFlagTasks(Player p){
        removeRedFlag(GameProperties.redFlagCurrentLocation);
        GameProperties.redFlagCarrier = p;
        GameProperties.redFlagCurrentLocation = null;
        GameProperties.redFlagOnGround = false;
        p.playSound(p.getLocation(), org.bukkit.Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 1, 1);
        Teams.playAlarm("Red");
        Teams.broadcastMessage(p.getName() + " stole your flag", "Red");
    }

    private static void playerStoleBlueFlagTasks(Player p){
        removeBlueFlag(GameProperties.blueFlagCurrentLocation);
        GameProperties.blueFlagCarrier = p;
        GameProperties.blueFlagCurrentLocation = null;
        GameProperties.blueFlagOnGround = false;
        p.playSound(p.getLocation(), org.bukkit.Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 1, 1);
        Teams.playAlarm("Blue");
        Teams.broadcastMessage(ChatColor.RED + p.getName() + " stole your flag", "Blue");
    }

    private static void playerCapturedRedFlagTasks(Player p){
        GameProperties.redFlagCarrier = null;
        GameProperties.redFlagCurrentLocation = GameProperties.redFlagLocationBase;
        GameProperties.redFlagOnGround = true;
        GameProperties.blueTeamScore++;
        Teams.playVictory("Blue");
    
        for(Player player : Bukkit.getOnlinePlayers()){
            player.sendMessage(ChatColor.BLUE + "Blue Team Scored");
        }
    }

    private static void playerCapturedBlueFlagTasks(Player p){
        GameProperties.blueFlagCarrier = null;
        GameProperties.blueFlagCurrentLocation = GameProperties.blueFlagLocationBase;
        GameProperties.blueFlagOnGround = true;
        GameProperties.redTeamScore++;
        Teams.playVictory("Red");
        
        for(Player player : Bukkit.getOnlinePlayers()){
            player.sendMessage(ChatColor.RED + "Red Team Scored");
        }
    }
}

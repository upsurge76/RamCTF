package me.ramctf;


import org.bukkit.Location;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Helpers {
    public static int[] get3AxisDistance(Location loc1, Location loc2) {
        
        int x = Math.abs(Math.abs(loc1.getBlockX()) - Math.abs(loc2.getBlockX()));
        int y = Math.abs(Math.abs(loc1.getBlockY()) - Math.abs(loc2.getBlockY()));
        int z = Math.abs(Math.abs(loc1.getBlockZ()) - Math.abs(loc2.getBlockZ()));
        return new int[]{x, y, z};
    }

    public static boolean withinDistance(Location loc1, Location loc2, int distance) {
        int[] axis = get3AxisDistance(loc1, loc2);
        return axis[0] <= distance && axis[1] <= distance && axis[2] <= distance;
    }
    
    public static void debug(){
        new BukkitRunnable() {
            @Override
            public void run() {
                System.out.println("");
                // System.out.println("Pre-Game Started: " + GameProperties.pregameStarted());
                // System.out.println("Game Started: " + GameProperties.gameStarted());
                // System.out.println("Team Setup Started: " + GameProperties.teamSetupStarted());
                // System.out.println("Team Setup Completed: " + GameProperties.teamSetupCompleted());
                System.out.println("Blue Flag Location Base: " + GameProperties.blueFlagLocationBase());
                System.out.println("Red Flag Location Base: " + GameProperties.redFlagLocationBase());
                System.out.println("Blue Flag Current Location: " + GameProperties.blueFlagCurrentLocation());
                System.out.println("Red Flag Current Location: " + GameProperties.redFlagCurrentLocation());
                // System.out.println("Blue Flag On Ground: " + GameProperties.blueFlagOnGround());
                // System.out.println("Red Flag On Ground: " + GameProperties.redFlagOnGround());
                // System.out.println("Blue Flag Carrier: " + GameProperties.blueFlagCarrier());
                // System.out.println("Red Flag Carrier: " + GameProperties.redFlagCarrier());
                // System.out.println("Red Team Score: " + GameProperties.redTeamScore());
                // System.out.println("Blue Team Score: " + GameProperties.blueTeamScore());
                // System.out.println("Pre-Game Timer: " + GameProperties.pregameTimer());
                // System.out.println("Game Timer: " + GameProperties.gameTimer());
                // System.out.println("Minutes Until Game Start: " + GameProperties.minutesUntilGameStart());
                // System.out.println("Minutes Until Game End: " + GameProperties.minutesUntilGameEnd());


            }
        }.runTaskTimer(JavaPlugin.getPlugin(RamCTF.class), 0, 30);
    }
}

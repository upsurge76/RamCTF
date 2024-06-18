package me.ramctf;
import org.bukkit.Location;

public class GameProperties {
    static boolean gameStarted = false;

    static boolean teamSetupStarted = false;
    static boolean teamSetupCompleted = false;
    
    static Location bluePlatformLocation = null;
    static Location redPlatformLocation = null;

    static boolean bluePlatformSpawned = false;
    static boolean redPlatformSpawned = false;

    static int redTeamScore = 0;
    static int blueTeamScore = 0;

    
}

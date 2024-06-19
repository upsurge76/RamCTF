package me.ramctf;
import org.bukkit.Location;

public class GameProperties {
    static boolean gameStarted = false;

    public static boolean teamSetupStarted = false;
    public static boolean teamSetupCompleted = false;
    
    public static Location blueFlagLocation = null;
    public static Location redFlagLocation = null;

    static int redTeamScore = 0;
    static int blueTeamScore = 0;

    public static int pregameTimer = 2;
    public static int gameTimer = 10;

    public static int minutesUntilGameStart = 0;

    public static boolean isGameReadyToStart(){
        return teamSetupCompleted && blueFlagLocation != null && redFlagLocation != null;
    }
}

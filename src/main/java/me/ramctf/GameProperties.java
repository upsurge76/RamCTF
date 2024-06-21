package me.ramctf;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class GameProperties {
    public static boolean pregameStarted = false;
    public static boolean gameStarted = false;

    public static boolean teamSetupStarted = false;
    public static boolean teamSetupCompleted = false;
    
    public static Location blueFlagLocationBase = null;
    public static Location redFlagLocationBase = null;

    public static Location blueFlagCurrentLocation = null;
    public static Location redFlagCurrentLocation = null;

    public static Boolean blueFlagOnGround = false;
    public static Boolean redFlagOnGround = false;

    public static Player blueFlagCarrier = null;
    public static Player redFlagCarrier = null;

    public static int redTeamScore = 0;
    public static int blueTeamScore = 0;

    public static int pregameTimer = 2;
    public static int gameTimer = 10;

    public static int minutesUntilGameStart = 0;
    public static int minutesUntilGameEnd = 0;
}

package me.ramctf;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class GameProperties {

    private GameProperties() {}

    private static boolean gamePaused = false;

    private static boolean pregameStarted = false;
    private static boolean gameStarted = false;

    private static boolean teamSetupStarted = false;
    private static boolean teamSetupCompleted = false;
    
    private static Location blueFlagLocationBase = null;
    private static Location redFlagLocationBase = null;

    private static Location blueFlagCurrentLocation = null;
    private static Location redFlagCurrentLocation = null;

    private static Boolean blueFlagOnGround = false;
    private static Boolean redFlagOnGround = false;

    private static Boolean pregamePlayerLootDrop = true;
    private static Boolean flagBuildProtection = true;
    private static Boolean doPregameHunger = true;
    private static Boolean hideScoreboardAndParticles = false;
    private static int flagProximityDistance = 15;

    private static Player blueFlagCarrier = null;
    private static Player redFlagCarrier = null;

    private static int redTeamScore = 0;
    private static int blueTeamScore = 0;

    private static int pregameDuration = 5;
    private static int gameDuration = 10;
    private static int flagAutorecoveryTimer = 60; //seconds

    private static int minutesUntilGameStart = 0;
    private static int minutesUntilGameEnd = 0;

    public static synchronized boolean isGamePaused() {
        return gamePaused;
    }

    public static synchronized int flagProximityDistance() {
        return flagProximityDistance;
    }

    public static synchronized boolean doPregameHunger() {
        return doPregameHunger;
    }

    public static synchronized boolean hideScoreboardAndParticles() {
        return hideScoreboardAndParticles;
    }

    public static synchronized boolean flagBuildProtection() {
        return flagBuildProtection;
    }

    public static synchronized boolean pregamePlayerLootDrop() {
        return pregamePlayerLootDrop;
    }

    public static synchronized int flagAutorecoveryTimer() {
        return flagAutorecoveryTimer;
    }

    public static synchronized boolean pregameRunning() {
        return pregameStarted;
    }

    public static synchronized boolean mainGameRunning() {
        return gameStarted;
    }

    public static synchronized boolean teamSetupStarted() {
        return teamSetupStarted;
    }

    public static synchronized boolean teamSetupCompleted() {
        return teamSetupCompleted;
    }

    public static synchronized Location blueFlagLocationBase() {
        return blueFlagLocationBase;
    }

    public static synchronized Location redFlagLocationBase() {
        return redFlagLocationBase;
    }

    public static synchronized Location blueFlagCurrentLocation() {
        return blueFlagCurrentLocation;
    }

    public static synchronized Location redFlagCurrentLocation() {
        return redFlagCurrentLocation;
    }

    public static synchronized Boolean blueFlagOnGround() {
        return blueFlagOnGround;
    }

    public static synchronized Boolean redFlagOnGround() {
        return redFlagOnGround;
    }

    public static synchronized Player blueFlagCarrier() {
        return blueFlagCarrier;
    }

    public static synchronized Player redFlagCarrier() {
        return redFlagCarrier;
    }

    public static synchronized int redTeamScore() {
        return redTeamScore;
    }

    public static synchronized int blueTeamScore() {
        return blueTeamScore;
    }

    public static synchronized int pregameTimer() {
        return pregameDuration;
    }

    public static synchronized int gameTimer() {
        return gameDuration;
    }

    public static synchronized int minutesUntilGameStart() {
        return minutesUntilGameStart;
    }

    public static synchronized int minutesUntilGameEnd() {
        return minutesUntilGameEnd;
    }

    public static synchronized void setGamePaused(boolean gamePaused) {
        GameProperties.gamePaused = gamePaused;
    }

    public static synchronized void setFlagProximityDistance(int flagProximityDistance) {
        GameProperties.flagProximityDistance = flagProximityDistance;
    }

    public static synchronized void setDoPregameHunger(boolean doHunger) {
        GameProperties.doPregameHunger = doHunger;
    }

    public static synchronized void setHideScoreboardAndParticles(boolean hideScoreboardAndParticles) {
        GameProperties.hideScoreboardAndParticles = hideScoreboardAndParticles;
    }

    public static synchronized void setFlagBuildProtection(boolean flagBuildProtection) {
        GameProperties.flagBuildProtection = flagBuildProtection;
    }

    public static synchronized void setPregamePlayerLootDrop(boolean playerLootDrop) {
        GameProperties.pregamePlayerLootDrop = playerLootDrop;
    }

    public static synchronized void setPregameRunning(boolean pregameStarted) {
        GameProperties.pregameStarted = pregameStarted;
    }

    public static synchronized void setMainGameRunning(boolean gameStarted) {
        GameProperties.gameStarted = gameStarted;
    }

    public static synchronized void setTeamSetupStarted(boolean teamSetupStarted) {
        GameProperties.teamSetupStarted = teamSetupStarted;
    }

    public static synchronized void setTeamSetupCompleted(boolean teamSetupCompleted) {
        GameProperties.teamSetupCompleted = teamSetupCompleted;
    }

    public static synchronized void setBlueFlagLocationBase(Location blueFlagLocationBase) {
        GameProperties.blueFlagLocationBase = blueFlagLocationBase;
    }

    public static synchronized void setRedFlagLocationBase(Location redFlagLocationBase) {
        GameProperties.redFlagLocationBase = redFlagLocationBase;
    }

    public static synchronized void setBlueFlagCurrentLocation(Location blueFlagCurrentLocation) {
        GameProperties.blueFlagCurrentLocation = blueFlagCurrentLocation;
    }

    public static synchronized void setRedFlagCurrentLocation(Location redFlagCurrentLocation) {
        GameProperties.redFlagCurrentLocation = redFlagCurrentLocation;
    }

    public static synchronized void setBlueFlagOnGround(Boolean blueFlagOnGround) {
        GameProperties.blueFlagOnGround = blueFlagOnGround;
    }

    public static synchronized void setRedFlagOnGround(Boolean redFlagOnGround) {
        GameProperties.redFlagOnGround = redFlagOnGround;
    }

    public static synchronized void setBlueFlagCarrier(Player blueFlagCarrier) {
        GameProperties.blueFlagCarrier = blueFlagCarrier;
    }

    public static synchronized void setRedFlagCarrier(Player redFlagCarrier) {
        GameProperties.redFlagCarrier = redFlagCarrier;
    }

    public static synchronized void setRedTeamScore(int redTeamScore) {
        GameProperties.redTeamScore = redTeamScore;
    }

    public static synchronized void setBlueTeamScore(int blueTeamScore) {
        GameProperties.blueTeamScore = blueTeamScore;
    }

    public static synchronized void setPregameDuration(int pregameTimer) {
        GameProperties.pregameDuration = pregameTimer;
    }

    public static synchronized void setGameDuration(int gameTimer) {
        GameProperties.gameDuration = gameTimer;
    }

    public static synchronized void setMinutesUntilGameStart(int minutesUntilGameStart) {
        GameProperties.minutesUntilGameStart = minutesUntilGameStart;
    }

    public static synchronized void setMinutesUntilGameEnd(int minutesUntilGameEnd) {
        GameProperties.minutesUntilGameEnd = minutesUntilGameEnd;
    }

    public static synchronized void setFlagAutorecoveryTimer(int flagAutorecoveryTimer) {
        GameProperties.flagAutorecoveryTimer = flagAutorecoveryTimer;
    }

}

package me.ramctf;


public class GameManager {
    public static boolean isGameReadyToStart() {
        return GameProperties.teamSetupCompleted && GameProperties.blueFlagLocationBase != null && GameProperties.redFlagLocationBase != null;
    }

    public static void startPregame() {
        
        GameProperties.pregameStarted = true;

        GameProperties.blueFlagOnGround = true;
        GameProperties.redFlagOnGround = true;
        GameProperties.blueFlagCurrentLocation = GameProperties.blueFlagLocationBase;
        GameProperties.redFlagCurrentLocation = GameProperties.redFlagLocationBase;
        
        // Runnable.teleportCountDownDelay();
    }

    public static void startGame(){
        GameProperties.gameStarted = true;
        GameProperties.pregameStarted = false;

        GameProperties.blueFlagOnGround = true;
        GameProperties.redFlagOnGround = true;
        GameProperties.blueFlagCurrentLocation = GameProperties.blueFlagLocationBase;
        GameProperties.redFlagCurrentLocation = GameProperties.redFlagLocationBase;

        GameProperties.minutesUntilGameStart = 0;
        GameProperties.minutesUntilGameEnd = GameProperties.gameTimer;

        Runnable.gameTimer(0, GameProperties.gameTimer);

    }
}

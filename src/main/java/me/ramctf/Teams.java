package me.ramctf;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.*;



public class Teams implements Listener{

    //variables for teams and scoreboard
    static Team red;
    static Team blue;
    static Scoreboard board;
    static Objective obj;
    static Team BlueInfo;
    static Team RedInfo;
    static Team GameInfo;

    public static void initializeTeams(){

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        board = manager.getNewScoreboard();
        obj = board.registerNewObjective("MainScoreboard","dummy", ChatColor.BOLD + "------RAM-CTF------");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        GameInfo = board.registerNewTeam("GameInfo");
        GameInfo.addEntry(ChatColor.AQUA.toString());
        GameInfo.setPrefix(ChatColor.GREEN + "");
        GameInfo.setSuffix("");
        obj.getScore(ChatColor.AQUA.toString()).setScore(2);

        BlueInfo = board.registerNewTeam("BlueInfo");
        BlueInfo.addEntry(ChatColor.WHITE.toString());
        BlueInfo.setPrefix(ChatColor.BLUE + "Blue Score: ");
        BlueInfo.setSuffix("");
        obj.getScore(ChatColor.WHITE.toString()).setScore(1);

        RedInfo = board.registerNewTeam("RedInfo");
        RedInfo.addEntry(ChatColor.BLACK.toString());
        RedInfo.setPrefix(ChatColor.RED + "Red Score: ");
        RedInfo.setSuffix("");
        obj.getScore(ChatColor.BLACK.toString()).setScore(0);

        blue = board.registerNewTeam("Blue");
        blue.setPrefix(ChatColor.BLUE + "[BLUE] " + ChatColor.WHITE);
        blue.setAllowFriendlyFire(false);
        blue.setCanSeeFriendlyInvisibles(true);

        red = board.registerNewTeam("Red");
        red.setPrefix(ChatColor.RED + "[RED] " + ChatColor.WHITE);
        red.setAllowFriendlyFire(false);
        red.setCanSeeFriendlyInvisibles(true);

    }   

    public static void updateScoreboard(){
        for(Player p : Bukkit.getOnlinePlayers()){
            RedInfo.setSuffix(ChatColor.GOLD + Integer.toString(GameProperties.redTeamScore));
            BlueInfo.setSuffix(ChatColor.GOLD + Integer.toString(GameProperties.blueTeamScore));
            if(!GameProperties.gameStarted){
                if(GameProperties.minutesUntilGameStart > 0){
                    GameInfo.setSuffix(ChatColor.GREEN + "Game Starting in " + ChatColor.GOLD + GameProperties.minutesUntilGameStart + " minutes");
                } else {
                    GameInfo.setSuffix(ChatColor.GREEN + "Waiting for game start");
                }
            }
            p.setScoreboard(board);
        }
    }

    public static boolean checkAllPlayerTeams(){
        for(Player online : Bukkit.getOnlinePlayers()){
            if(!red.hasEntry(online.getName()) && !blue.hasEntry(online.getName())){
                return false;
            }
        }
        return true;
    }

    public static void addPlayerBlueTeam(Player p){
        blue.addEntry(p.getName());

        if(red.hasEntry(p.getName())){
            red.removeEntry(p.getName());
        }

        if(checkAllPlayerTeams()){
            GameProperties.teamSetupCompleted = true;
        }
        
    }

    public static void addPlayerRedTeam(Player p){
        red.addEntry(p.getName());

        if(blue.hasEntry(p.getName())){
            blue.removeEntry(p.getName());
        }

        if(checkAllPlayerTeams()){
            GameProperties.teamSetupCompleted = true;
        }
        
    }

    public static String getTeam(Player p){
        if(red.hasEntry(p.getName())){
            return "Red";
        } else if(blue.hasEntry(p.getName())){
            return "Blue";
        } else {
            return "None";
        }
    }
}



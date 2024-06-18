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
    Location redflag;
    Location blueflag;
    static Score bluescore;
    static Score redscore;
    static Scoreboard board;
    static Objective obj;

    @EventHandler
    public void onPlayerJoin(Player p){
        updateScoreboard();
    }

    public static void initializeTeams(){

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        board = manager.getNewScoreboard();
        obj = board.registerNewObjective("MainScoreboard","dummy", ChatColor.BOLD + "------RAM-CTF------");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        bluescore = obj.getScore(ChatColor.BLUE + "BLUE SCORE: " + ChatColor.GOLD);
        redscore = obj.getScore(ChatColor.RED + "RED SCORE: " + ChatColor.GOLD);
        redscore.setScore(0);
        bluescore.setScore(0);

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
            bluescore = obj.getScore(ChatColor.BLUE + "BLUE SCORE: " + ChatColor.GOLD);
            redscore = obj.getScore(ChatColor.RED + "RED SCORE: " + ChatColor.GOLD);
            redscore.setScore(GameProperties.redTeamScore);
            bluescore.setScore(GameProperties.blueTeamScore);
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



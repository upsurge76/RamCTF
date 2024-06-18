package me.ramctf;


import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreateTeamInventory implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if(sender instanceof Player){
            if(!GameProperties.gameStarted){
                if(GameProperties.teamSetupStarted){
                    TeamHomePage.ShowHomePage(p);
                    return true;
                } else {
                    sender.sendMessage("Game host has not started team setup.");
                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, 3);
                    return true;
                }
            } else {
                sender.sendMessage("Game has already started");
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, 3);
                return true;
            }
        }
        return false;
    }

}
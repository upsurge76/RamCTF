package me.ramctf.CommandCatcher;


import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.ramctf.GameProperties;
import me.ramctf.InventoryPages.TeamPage;

public class CreateTeamInventory implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if(sender instanceof Player){
            if(!GameProperties.teamSetupStarted()){
                sender.sendMessage("Game host has not started team setup.");
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, 3);
                return true;
            }
            if(GameProperties.pregameStarted() || GameProperties.gameStarted()){
                sender.sendMessage("Game has already started. You cannot change teams now.");
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, 3);
                return true;
            }
            if(GameProperties.teamSetupStarted()){
                TeamPage.ShowHomePage(p);
                return true;
            }
        }
        return false;
    }

}
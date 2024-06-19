package me.ramctf;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class CreateSetupInventory implements CommandExecutor {
    

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player){
            if(sender.isOp()){
                if(!GameProperties.gameStarted){
                    Player p = (Player) sender;
                    SetupPage.ShowHomePage(p);
                    return true;
                } 
            } else {
                sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
            }

        }
        return false;
    }

}
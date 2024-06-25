package me.ramctf.EventHandler;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import me.ramctf.GameProperties;
import me.ramctf.Helpers;

public class BlockInteractHandler implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if(GameProperties.flagBuildProtection()){
            Block b = e.getBlock();
            int x, z;

            if(GameProperties.blueFlagLocationBase() != null){
                x = Helpers.get3AxisDistance(b.getLocation(), GameProperties.blueFlagLocationBase())[0];
                z = Helpers.get3AxisDistance(b.getLocation(), GameProperties.blueFlagLocationBase())[2];

                if(x < 3 && z < 3){
                    e.setCancelled(true);
                }
            }

            if(GameProperties.redFlagLocationBase() != null){
                x = Helpers.get3AxisDistance(b.getLocation(), GameProperties.redFlagLocationBase())[0];
                z = Helpers.get3AxisDistance(b.getLocation(), GameProperties.redFlagLocationBase())[2];

                if(x < 3 && z < 3){
                    e.setCancelled(true);
                }
            }
            
            if(b.getLocation().equals(GameProperties.blueFlagCurrentLocation()) || b.getLocation().equals(GameProperties.redFlagCurrentLocation())){
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){

        if(GameProperties.flagBuildProtection()){
            Block b = e.getBlock();
            
            int x, z;

            if(GameProperties.blueFlagLocationBase() != null){
                x = Helpers.get3AxisDistance(b.getLocation(), GameProperties.blueFlagLocationBase())[0];
                z = Helpers.get3AxisDistance(b.getLocation(), GameProperties.blueFlagLocationBase())[2];
        
                if(x < 3 && z < 3){
                    e.setCancelled(true);
                }
            }

            if(GameProperties.redFlagLocationBase() != null){
                x = Helpers.get3AxisDistance(b.getLocation(), GameProperties.redFlagLocationBase())[0];
                z = Helpers.get3AxisDistance(b.getLocation(), GameProperties.redFlagLocationBase())[2];
        
                if(x < 3 && z < 3){
                    e.setCancelled(true);
                }
            }
        }

    }

}

package me.ramctf.EventHandler;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffectType;

import me.ramctf.FlagAutoRecoveryTimer;
import me.ramctf.GameProperties;
import me.ramctf.Helpers;
import me.ramctf.Teams;
import net.md_5.bungee.api.ChatColor;

public class DeathHandler implements Listener{

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){
        Player p = e.getEntity().getPlayer();
        Player killer = p.getKiller();

        e.getDrops().removeIf(item -> isLeatherChestplateOfColor(item, Color.RED) || isLeatherChestplateOfColor(item, Color.BLUE));

        if(GameProperties.mainGameRunning()){
            Helpers.cancelLootDrop(e);
        }

        if(killer != null){
            ItemStack dirtBlocks = new ItemStack(Material.DIRT, 8);
            p.getWorld().dropItemNaturally(p.getLocation(), dirtBlocks);
            killer.addPotionEffect(PotionEffectType.REGENERATION.createEffect(80, 1));
            if(GameProperties.pregamePlayerLootDrop()){
                if(!Teams.getTeam(killer).equals(Teams.getTeam(p))){
                    if(Teams.getTeam(p).equals("Red")){
                        if(!Helpers.withinDistance(p.getLocation(), GameProperties.blueFlagLocationBase(), GameProperties.flagProximityDistance())){
                            Helpers.cancelLootDrop(e);
                    } else if(Teams.getTeam(p).equals("Blue")){
                        if(!Helpers.withinDistance(p.getLocation(), GameProperties.redFlagLocationBase(), GameProperties.flagProximityDistance())){
                            Helpers.cancelLootDrop(e);
                            }
                        } 
                    }
                    
                } 
            }
        }

        if(GameProperties.blueFlagCarrier() != null){
            if(GameProperties.blueFlagCarrier().equals(p)){
                GameProperties.setBlueFlagCarrier(null);
                GameProperties.setBlueFlagOnGround(true);
                if(p.getLastDamageCause().getCause() != EntityDamageEvent.DamageCause.VOID){
                    GameProperties.setBlueFlagCurrentLocation(p.getLocation().getBlock().getLocation());
                    Teams.broadcastMessage(ChatColor.GREEN + p.getName() + " dropped your flag", "Red", p);
                    Teams.broadcastMessage(ChatColor.RED + p.getName() + " dropped enemy flag", "Blue", p);
                    FlagAutoRecoveryTimer.startFlagTimer("Blue");
                } else {
                    GameProperties.setBlueFlagCurrentLocation(GameProperties.blueFlagLocationBase());
                    Teams.broadcastMessage(ChatColor.GREEN + p.getName() + " died in void. Flag recovered", "Red", p);
                    Teams.broadcastMessage(ChatColor.RED + p.getName() + " died in void. Enemy flag at base", "Blue", p);
                }

            }
        }
        if(GameProperties.redFlagCarrier() != null){
            if(GameProperties.redFlagCarrier().equals(p)){
                GameProperties.setRedFlagCarrier(null);
                GameProperties.setRedFlagOnGround(true);
                
                if(p.getLastDamageCause().getCause() != EntityDamageEvent.DamageCause.VOID){
                    GameProperties.setRedFlagCurrentLocation(p.getLocation().getBlock().getLocation());
                    Teams.broadcastMessage(ChatColor.GREEN + p.getName() + " dropped your flag", "Blue", p);
                    Teams.broadcastMessage(ChatColor.RED + p.getName() + " dropped enemy flag", "Red", p);
                    FlagAutoRecoveryTimer.startFlagTimer("Red");
                } else {
                    GameProperties.setRedFlagCurrentLocation(GameProperties.redFlagLocationBase());
                    Teams.broadcastMessage(ChatColor.GREEN + p.getName() + " died in void. Flag recovered", "Blue", p);
                    Teams.broadcastMessage(ChatColor.RED + p.getName() + " died in void. Enemy flag at base", "Red", p);
                }
            }
        } 
    }

    private boolean isLeatherChestplateOfColor(ItemStack item, Color color) {
        if (item.getType() != Material.LEATHER_CHESTPLATE) {
            return false;
        }

        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        return meta != null && color.equals(meta.getColor());
    }
}

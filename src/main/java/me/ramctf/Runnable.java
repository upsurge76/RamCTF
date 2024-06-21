package me.ramctf;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.Material;
import org.bukkit.ChatColor;

public class Runnable {

    private static int pregameTicksElapsed = 1;
    private static int gameTicksElapsed = 1;
    private static boolean pregameAlertPlayed = false;

    public static void runEvery2Ticks() {

        ItemStack redLeatherChestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta redLeatherChestplateMeta = (LeatherArmorMeta) redLeatherChestplate.getItemMeta();
        redLeatherChestplateMeta.setDisplayName(ChatColor.RED + "Red Team");
        redLeatherChestplateMeta.setColor(Color.RED);
        redLeatherChestplate.setItemMeta(redLeatherChestplateMeta);

        ItemStack blueLeatherChestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta blueLeatherChestplateMeta = (LeatherArmorMeta) blueLeatherChestplate.getItemMeta();
        blueLeatherChestplateMeta.setDisplayName(ChatColor.BLUE + "Blue Team");
        blueLeatherChestplateMeta.setColor(Color.BLUE);
        blueLeatherChestplate.setItemMeta(blueLeatherChestplateMeta);

        new BukkitRunnable() {
            @Override
            public void run() {

                Teams.updateScoreboard();

                for(Player p : Bukkit.getOnlinePlayers()){
                    String team = Teams.getTeam(p);
                    if(!team.equalsIgnoreCase("None")){
                        if(team.equalsIgnoreCase("Blue")){
                            PlayerInventory inventory = p.getInventory();
                            ItemStack[] armor = inventory.getArmorContents();
                            armor[2] = blueLeatherChestplate;
                            p.getInventory().setArmorContents(armor);
                            
                        } else if(team.equalsIgnoreCase("Red")){
                            PlayerInventory inventory = p.getInventory();
                            ItemStack[] armor = inventory.getArmorContents();
                            armor[2] = redLeatherChestplate;
                            p.getInventory().setArmorContents(armor);
                            
                        }
                    }
                }
                
                if(GameProperties.blueFlagLocationBase != null && !GameProperties.blueFlagOnGround){
                    DustOptions dustOptions = new DustOptions(Color.fromRGB(0, 0, 255), 1.0F);
                    Location particleLocation = GameProperties.blueFlagLocationBase.clone().add(0, 1, 0);
                    particleLocation.getWorld().spawnParticle(Particle.REDSTONE, particleLocation, 25, 0.2, 0.4, 0.2, dustOptions);


                }
                if(GameProperties.redFlagLocationBase != null && !GameProperties.redFlagOnGround){
                    DustOptions dustOptions = new DustOptions(Color.fromRGB(255, 0, 0), 1.0F);
                    Location particleLocation = GameProperties.redFlagLocationBase.clone().add(0, 1, 0);
                    particleLocation.getWorld().spawnParticle(Particle.REDSTONE, particleLocation, 25, 0.2, 0.4, 0.2, dustOptions);

                }
                
                if(GameProperties.blueFlagOnGround){
                    FlagLogic.spawnBlueFlag(GameProperties.blueFlagCurrentLocation);
                }
                
                if(GameProperties.redFlagOnGround){
                    FlagLogic.spawnRedFlag(GameProperties.redFlagCurrentLocation);
                }
            
            }
        }.runTaskTimer(JavaPlugin.getPlugin(RamCTF.class), 0, 2);
    }

    public static void teleportCountDownDelay(){

        for(Player p : Bukkit.getOnlinePlayers()){
            p.playSound(p.getLocation(), org.bukkit.Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
            p.sendTitle(ChatColor.GREEN + "", "You will be teleported to your flag in 3 seconds", 0, 60, 5);
        }
        
        new BukkitRunnable() {

            @Override
            public void run() {
                for(Player p : Bukkit.getOnlinePlayers()){
                    String team = Teams.getTeam(p);
                    if(team.equalsIgnoreCase("Blue")){
                        p.teleport(GameProperties.blueFlagLocationBase.clone().add(0, 1, 0));
                    } else if(team.equalsIgnoreCase("Red")){
                        p.teleport(GameProperties.redFlagLocationBase.clone().add(0, 1, 0));
                    }
                }
                cancel();
                displayGameStartMessage();
                pregameTicksElapsed = 0;
                }

            }.runTaskTimer(JavaPlugin.getPlugin(RamCTF.class), 60, 1);

    }

    private static void displayGameStartMessage() {
        for(Player p : Bukkit.getOnlinePlayers()){
            p.sendTitle(ChatColor.GREEN + "", "You have " + String.valueOf(GameProperties.pregameTimer) + " minutes to collect items before the game begins", 5, 60, 5);
            p.playSound(p.getLocation(), org.bukkit.Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
        }
        pregameTimer();
    }

    private static void pregameTimer() {
        GameProperties.minutesUntilGameStart = GameProperties.pregameTimer;
        new BukkitRunnable() {
            @Override
            public void run() {
                pregameTicksElapsed++;
                if(pregameTicksElapsed % (20*60) == 0){
                    GameProperties.minutesUntilGameStart--;
                }
                if(GameProperties.minutesUntilGameStart == 0){
                    GameProperties.pregameStarted = false;
                    GameProperties.gameStarted = true;
                    pregameAlertPlayed = false;
                    GameManager.startGame();
                    cancel();

                }
                if(GameProperties.minutesUntilGameStart == 1 && !pregameAlertPlayed){
                    for(Player p : Bukkit.getOnlinePlayers()){
                        p.sendTitle(ChatColor.GREEN + "", "Game starting in 1 minute", 5, 60, 5);
                        p.playSound(p.getLocation(), org.bukkit.Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
                        pregameAlertPlayed = true;
                    }
                }
            }
        }.runTaskTimer(JavaPlugin.getPlugin(RamCTF.class), 0, 1);
    }

    public static void gameTimer(int delay, int durationMins){
        new BukkitRunnable() {
            @Override
            public void run() {
                FlagLogic.calculateCaptures();

                gameTicksElapsed++;
                if(gameTicksElapsed == durationMins * 60 * 20){
                    GameProperties.pregameStarted = false;
                    GameProperties.minutesUntilGameStart = 0;
                    GameProperties.blueTeamScore = 0;
                    GameProperties.redTeamScore = 0;
                    cancel();
                }
            }
        }.runTaskTimer(JavaPlugin.getPlugin(RamCTF.class), delay, 1);
    }

}
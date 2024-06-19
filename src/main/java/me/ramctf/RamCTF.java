package me.ramctf;
import org.bukkit.plugin.java.JavaPlugin;

public class RamCTF extends JavaPlugin {
    
    @Override
    public void onEnable() {
        getCommand("setup").setExecutor(new CreateSetupInventory());
        getCommand("team").setExecutor(new CreateTeamInventory());
        getServer().getPluginManager().registerEvents(new SetupPage(), this);
        getServer().getPluginManager().registerEvents(new TeamPage(), this);
        getServer().getPluginManager().registerEvents(new Teams(), this);
        getServer().getPluginManager().registerEvents(new GameSettingsPage(), this);
        getServer().getPluginManager().registerEvents(new DelaySettingsPage(), this);
        getServer().getPluginManager().registerEvents(new ArmorStealPreventing(), this);
        Teams.initializeTeams();
        Runnable.runEvery2Ticks();
    }
}

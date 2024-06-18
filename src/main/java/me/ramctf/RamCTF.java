package me.ramctf;
import org.bukkit.plugin.java.JavaPlugin;

public class RamCTF extends JavaPlugin {
    
    @Override
    public void onEnable() {
        getCommand("setup").setExecutor(new CreateSetupInventory());
        getCommand("team").setExecutor(new CreateTeamInventory());
        getServer().getPluginManager().registerEvents(new SetupHomePage(), this);
        getServer().getPluginManager().registerEvents(new TeamHomePage(), this);
        getServer().getPluginManager().registerEvents(new Teams(), this);
        Runnable.updateScoreboard();
    }
}

package me.simplistic.minigame;

import me.simplistic.minigame.command.GameCommand;
import me.simplistic.minigame.listener.ConnectListener;
import me.simplistic.minigame.listener.EntityDamageEvent;
import me.simplistic.minigame.listener.InventoryListener;
import me.simplistic.minigame.listener.PlayerDeathListener;
import me.simplistic.minigame.manager.ArenaManager;
import me.simplistic.minigame.manager.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Minigame extends JavaPlugin {

    private ArenaManager arenaManager;

    @Override
    public void onEnable() {
        // Startup Logic
        ConfigManager.setupConfig(this);
        arenaManager = new ArenaManager(this);

        getCommand("game").setExecutor(new GameCommand(this));
        Bukkit.getServer().getPluginManager().registerEvents(new InventoryListener(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new ConnectListener(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerDeathListener(this), this);
    }

    public ArenaManager getArenaManager() { return arenaManager; }
}

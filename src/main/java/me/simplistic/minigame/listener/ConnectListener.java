package me.simplistic.minigame.listener;

import me.simplistic.minigame.Minigame;
import me.simplistic.minigame.instance.Arena;
import me.simplistic.minigame.manager.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ConnectListener implements Listener {

    private Minigame minigame;

    public ConnectListener(Minigame minigame){
        this.minigame = minigame;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.teleport(ConfigManager.getLobbySpawn());
        Bukkit.getServer().broadcastMessage(ChatColor.GRAY + "[" + ChatColor.YELLOW + "+" + ChatColor.GRAY + "]" + ChatColor.GREEN + " " + player.getName() + " has joined the server!");

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        Bukkit.getServer().broadcastMessage(ChatColor.GRAY + "[" + ChatColor.YELLOW + "-" + ChatColor.GRAY + "]" + ChatColor.GREEN + " " + player.getName() + " has left the server!");

        Arena arena = minigame.getArenaManager().getArena(event.getPlayer());
        if (arena != null) {
            arena.removePlayer(event.getPlayer());
        }

    }
}

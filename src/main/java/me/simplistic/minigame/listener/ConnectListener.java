package me.simplistic.minigame.listener;

import me.simplistic.minigame.Minigame;
import me.simplistic.minigame.instance.Arena;
import me.simplistic.minigame.manager.ConfigManager;
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

        event.getPlayer().teleport(ConfigManager.getLobbySpawn());

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){

        Arena arena = minigame.getArenaManager().getArena(event.getPlayer());
        if (arena != null) {
            arena.removePlayer(event.getPlayer());
        }

    }
}

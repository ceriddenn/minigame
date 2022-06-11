package me.simplistic.minigame.listener;

import me.simplistic.minigame.GameState;
import me.simplistic.minigame.Minigame;
import me.simplistic.minigame.instance.Arena;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerDeathListener implements Listener {

    private Minigame minigame;

    public PlayerDeathListener(Minigame minigame) {
        this.minigame = minigame;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player killer = event.getEntity().getKiller();
        Player victim = event.getEntity();

        Arena arena = minigame.getArenaManager().getArena(killer);


        if (arena.getState() == (GameState.LIVE) && arena != null) {
            arena.getGame().whoWon(killer);
            new BukkitRunnable() {public void run() {
                victim.spigot().respawn();
            }}.runTaskLater(minigame,1);

        }

    }

}

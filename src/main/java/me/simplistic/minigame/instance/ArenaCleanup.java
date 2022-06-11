package me.simplistic.minigame.instance;

import me.simplistic.minigame.Minigame;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class ArenaCleanup extends BukkitRunnable {

    private Minigame minigame;
    private Arena arena;
    private int countdownSeconds;

    public ArenaCleanup(Minigame minigame, Arena arena) {
        this.minigame = minigame;
        this.arena = arena;
        this.countdownSeconds = 3;

    }
    public void start() {
        arena.setSpectateForCleanup();
        runTaskTimer(minigame, 0, 20);
    }

    @Override
    public void run() {
        if (countdownSeconds == 0){
            arena.reset(true);
            cancel();
            return;
        }

        if(countdownSeconds <= 5) {
            arena.sendMessage(ChatColor.GREEN + "Returning to lobby in " + countdownSeconds + " second" + (countdownSeconds == 1 ? "" : "s") + ".");
        }
        countdownSeconds--;
    }
}

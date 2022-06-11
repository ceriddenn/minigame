package me.simplistic.minigame.instance;

import me.simplistic.minigame.GameState;
import me.simplistic.minigame.Minigame;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class Game {

    private Arena arena;
    private Minigame minigame;
    private HashMap<UUID, Integer> points;
    private ArenaCleanup cleanup;
    public Game(Minigame minigame, Arena arena) {
        this.arena = arena;
        points = new HashMap<>();
        this.minigame = minigame;
        this.cleanup = new ArenaCleanup(minigame, arena);

    }

    public void start() {
        arena.setState(GameState.LIVE);
        arena.sendMessage(ChatColor.GREEN + "Game has started!");

        for (UUID uuid : arena.getPlayers()) {
            points.put(uuid, 0);
        }
    }

    public void whoWon(Player player) {
            arena.sendMessage(ChatColor.GOLD + player.getName() + " " + "Has won the duel! GG");
            cleanup.start();

    }
}

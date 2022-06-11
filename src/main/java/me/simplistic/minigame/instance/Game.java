package me.simplistic.minigame.instance;

import me.simplistic.minigame.GameState;
import me.simplistic.minigame.Minigame;
import me.simplistic.minigame.manager.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class Game {

    private UUID uuid1;
    private UUID uuid2;

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

        uuid1 = arena.getPlayers().get(0);
        uuid2 = arena.getPlayers().get(1);

        Bukkit.getPlayer(uuid1).teleport(ConfigManager.getArenaPos1());
        Bukkit.getPlayer(uuid2).teleport(ConfigManager.getArenaPos2());

        arena.sendMessage(ChatColor.GREEN + "The duel has started!");
    }

    public void whoWon(Player player) {
            arena.sendMessage(ChatColor.GOLD + player.getName() + " won the duel! GG");
            arena.sendTitle(ChatColor.GREEN + "GG", ChatColor.YELLOW + player.getName() + " won this duel!");
            cleanup.start();

    }
}

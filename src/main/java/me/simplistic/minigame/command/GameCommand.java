package me.simplistic.minigame.command;

import me.simplistic.minigame.GameState;
import me.simplistic.minigame.Minigame;
import me.simplistic.minigame.instance.Arena;
import me.simplistic.minigame.manager.ArenaManager;
import me.simplistic.minigame.manager.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GameCommand implements CommandExecutor {

    private Minigame minigame;

    public GameCommand(Minigame minigame) {
        this.minigame = minigame;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if(args.length == 1 && args[0].equalsIgnoreCase("list")) {
                player.sendMessage(ChatColor.GREEN + "These are the available arenas!");
                for (Arena arena : minigame.getArenaManager().getArenas()) {
                    player.sendMessage(ChatColor.GREEN + "- " + arena.getId() + "  " + arena.getState().name() + "!");
                }
            } else if(args.length == 1 && args[0].equalsIgnoreCase("leave")) {
                Arena arena = minigame.getArenaManager().getArena(player);
                if (arena != null) {
                    player.sendMessage(ChatColor.GREEN + "Removing you from this game!");
                    arena.removePlayer(player);
                } else {
                player.sendMessage(ChatColor.RED + "You are already in a game!");
                }
            } else if (args.length == 1 && args[0].equalsIgnoreCase("gui")) {
                Inventory inv = Bukkit.createInventory(player, 9, "Arenas...");

                for (Arena arena : minigame.getArenaManager().getArenas()) {
                    ItemStack item = new ItemStack(Material.WOOL);
                    ItemMeta meta = item.getItemMeta();
                    meta.setDisplayName(ChatColor.GREEN + "Arena " + arena.getId());
                    meta.setLore(Arrays.asList(ChatColor.LIGHT_PURPLE + "Status: " + ChatColor.GREEN + arena.getState(), (arena.getState().equals(GameState.LIVE)? ChatColor.RED + "CLOSED" : ChatColor.GREEN + "OPEN"), arena.getPlayers().size() + "/" + ConfigManager.getRequiredPlayers()));
                    item.setItemMeta(meta);
                    inv.setItem(arena.getId(), item);
                }
                player.openInventory(inv);


            } else if(args.length == 2 && args[0].equalsIgnoreCase("join")) {
                if (minigame.getArenaManager().getArena(player) != null) {
                    player.sendMessage(ChatColor.RED + "You are already playing in an arena");
                    return false;
                }
                int id;
                try {
                    id = Integer.parseInt(args[1]);

                } catch (NumberFormatException e) {
                    player.sendMessage(ChatColor.RED + "You specified an invalid arena ID.");
                    return false;
                }

                if (id >= 0 && id < minigame.getArenaManager().getArenas().size()) {
                    Arena arena = minigame.getArenaManager().getArena(id);
                    if (arena.getState() == GameState.RECRUITING || arena.getState() == GameState.COUNTDOWN) {
                        player.sendMessage(ChatColor.GREEN  + "You are now playing in Arena " + id + ".");
                        arena.addPlayer(player);
                    } else {
                        player.sendMessage(ChatColor.RED + "You cannot join this arena while its LIVE!");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "You specified an invalid arena ID.");
                }
            } else {
                player.sendMessage(ChatColor.RED + "Invalid Usage!");
            }
        }


        return false;
    }
}

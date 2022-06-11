package me.simplistic.minigame.listener;

import me.simplistic.minigame.GameState;
import me.simplistic.minigame.Minigame;
import me.simplistic.minigame.instance.Arena;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryListener implements Listener {

    private Minigame minigame;

    public InventoryListener(Minigame minigame) {
        this.minigame = minigame;
    }

    @EventHandler
    public void onOpen(InventoryClickEvent event) {
        event.setCancelled(true);
        Player player = (Player) event.getWhoClicked();
        if (event.getView().getTitle().equals("Arenas...") && event.getCurrentItem() != null) {
            if (event.getCurrentItem().getType() == Material.WOOL) {
                int idxn = event.getSlot();
                if (minigame.getArenaManager().getArena(player) != null) {
                    player.sendMessage(ChatColor.RED + "You are already playing in an arena");
                    return;
                }
                int id;
                try {
                    id = idxn;

                } catch (NumberFormatException e) {
                    player.sendMessage(ChatColor.RED + "You specified an invalid arena ID.");
                    return;
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
            }

        }



    }

}

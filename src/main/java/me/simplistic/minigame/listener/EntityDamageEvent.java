package me.simplistic.minigame.listener;

import me.simplistic.minigame.Minigame;
import me.simplistic.minigame.instance.Arena;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageEvent implements Listener {

    private Minigame minigame;

    public EntityDamageEvent(Minigame minigame) {
        this.minigame = minigame;
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        Player victim = (Player) event.getEntity();
        Arena arena = minigame.getArenaManager().getArena(victim);

        if (arena != null) {
            if (damager instanceof Player) {
                event.setCancelled(false);
            } else {
                event.setCancelled(true);
            }
        }
    }

}

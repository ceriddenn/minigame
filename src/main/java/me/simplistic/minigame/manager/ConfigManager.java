package me.simplistic.minigame.manager;

import me.simplistic.minigame.Minigame;
import me.simplistic.minigame.instance.Arena;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {

    private static Location loc1;
    private static Location loc2;
    private static FileConfiguration config;
    private  Minigame minigame;
    public static void setupConfig(Minigame minigame) {
        ConfigManager.config = minigame.getConfig();
        minigame.saveDefaultConfig();
    }

    public static int getRequiredPlayers() { return config.getInt("required-players"); }

    public static int getCountdownSeconds() { return config.getInt("countdown-seconds"); }

    public static Location getLobbySpawn() {
        return new Location(
                Bukkit.getWorld(config.getString("lobby-spawn.world")),
                config.getDouble("lobby-spawn.x"),
                config.getDouble("lobby-spawn.y"),
                config.getDouble("lobby-spawn.z"),
                (float) config.getDouble("lobby-spawn.yaw"),
                (float) config.getDouble("lobby-spawn.pitch"));
    }

    public static Location getArenaPos1() {
        FileConfiguration config1 = config;
        for (String str : config1.getConfigurationSection("arenas.").getKeys(false)) {
            loc1 = new Location(
                    Bukkit.getWorld(config1.getString("arenas." + str + ".pos1.world")),
                    config1.getDouble("arenas." + str + ".pos1.x"),
                    config1.getDouble("arenas." + str + ".pos1.y"),
                    config1.getDouble("arenas." + str + ".pos1.z"),
                    (float) config1.getDouble("arenas." + str + ".pos1.yaw"),
                    (float) config1.getDouble("arenas." + str + ".pos1.pitch"));
        }
        return loc1;

    }
    public static Location getArenaPos2() {
        FileConfiguration config2 = config;
        for (String str : config2.getConfigurationSection("arenas.").getKeys(false)) {
            loc2 = new Location(
                    Bukkit.getWorld(config2.getString("arenas." + str + ".pos2.world")),
                    config2.getDouble("arenas." + str + ".pos2.x"),
                    config2.getDouble("arenas." + str + ".pos2.y"),
                    config2.getDouble("arenas." + str + ".pos2.z"),
                    (float) config2.getDouble("arenas." + str + ".pos2.yaw"),
                    (float) config2.getDouble("arenas." + str + ".pos2.pitch"));
        }
        return loc2;

    }



}

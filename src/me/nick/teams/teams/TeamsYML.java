package me.nick.teams.teams;

import me.nick.teams.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

public class TeamsYML {

    static Main main;

    public TeamsYML(Main instance) {
        main = instance;
    }

    public static YamlConfiguration team = null;
    public static File teamFile = null;

    public static void reloadTeams() {
        if (teamFile == null) {
            teamFile = new File(Bukkit.getPluginManager().getPlugin("Teams").getDataFolder(), "teams.yml");
        }
        team = YamlConfiguration.loadConfiguration(teamFile);

        InputStream defConfigStream = Bukkit.getPluginManager().getPlugin("Teams").getResource("teams.yml");
        if (defConfigStream != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
            if (!teamFile.exists() || teamFile.length() == 0L) {
                team.setDefaults(defConfig);
            }
        }
    }

    public static FileConfiguration getTeams() {
        if (team == null) {
            reloadTeams();
        }
        return team;
    }

    public static void saveTeams() {
        if (team == null || teamFile == null) {
            return;
        }
        try {
            getTeams().save(teamFile);
        } catch (IOException ex) {
            Bukkit.getLogger().log(Level.SEVERE, "Could not save config " + teamFile, ex);
        }
    }
}

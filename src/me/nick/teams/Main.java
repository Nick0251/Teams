package me.nick.teams;

import me.nick.teams.events.Chat;
import me.nick.teams.teams.TeamCommand;
import me.nick.teams.teams.argument.TeamInfo;
import me.nick.teams.teams.TeamsYML;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Main plugin;
    public static PluginManager pm;

    @Override
    public void onEnable() {
        plugin = this;
        pm = Bukkit.getPluginManager();
        registerCommands();
        registerEvents();
        TeamsYML.getTeams().options().copyDefaults(true);
        saveConfig();
    }

    @Override
    public void onDisable() {

    }

    public void registerEvents() {
        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(new Chat(this), this);
    }

    public void registerCommands() {
        getCommand("team").setExecutor(new TeamCommand());
    }
}

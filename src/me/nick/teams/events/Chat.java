package me.nick.teams.events;

import me.nick.teams.Main;
import me.nick.teams.teams.TeamsYML;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Chat implements Listener {

    private Main plugin;

    public Chat(Main pl) {
        plugin = pl;
    }

    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        String teamName = TeamsYML.getTeams().getString("players." + p.getName() + ".team");
        if (teamName != null) {
            e.setFormat(ChatColor.AQUA + "(" + teamName + ")" + ChatColor.GRAY + " %s: %s");
        } else {
            e.setFormat("%s > %s");
        }

        System.out.println(teamName);
    }

}

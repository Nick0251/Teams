package me.nick.teams.messages;

import me.nick.teams.teams.TeamsYML;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Messages {

    public static void TEAM_HELP(Player p) {
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&m--------------------------------"));
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b/team create [teamname]"));
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b/team invite [player]"));
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b/team join"));
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b/team leave"));
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b/team kick"));
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b/team chat"));
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&m--------------------------------"));
    }

    public static void ALREADY_IN_TEAM(Player p) {
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou're already in a team sir!"));
    }

    public static void TEAM_CREATE_ARGS_ERROR(Player p) {
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cUsage: /team create [name]"));
    }

    public static void TEAM_CREATED(Player p) {
        p.sendMessage(ChatColor.AQUA + TeamsYML.getTeams().getString("players." + p.getName() + ".team") + ChatColor.GRAY + " was created " + ChatColor.GREEN + "successfully!");
    }

    public static void NOT_IN_TEAM(Player p) {
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou're not in a team!"));
    }

    public static void NOT_LEADER(Player p) {
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou're not the leader!"));
    }

    public static void TARGET_NOT_ONLINE(Player p) {
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cTarget is not online!"));
    }

    public static void TARGET_ALREADY_INVITED(Player p) {
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cTarget is already invited!"));
    }

    public static void INVITE_YOURSELF(Player p) {
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cDon't invite yourself cunt!"));
    }

    public static void PLAYER_IN_TEAM(Player p) {
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThat player is already in a team!"));
    }

    public static void PLAYER_NOT_ONLINE(Player p) {
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThat player is not online!"));
    }

    public static void NOT_INVITED(Player p) {
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThat player is not invited!"));
    }

    public static void NOT_INVITED2(Player p) {
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou're not invited!"));
    }

    public static void TEAM_NOT_EXISTING(Player p) {
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThat team doesn't exist!"));
    }

    public static void TEAM_NAME_USED(Player p) {
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThat team name is already used!"));
    }
}

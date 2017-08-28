package me.nick.teams.teams.argument;

import me.nick.teams.Main;
import me.nick.teams.commands.CommandArgument;
import me.nick.teams.messages.Messages;
import me.nick.teams.teams.TeamsYML;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TeamLeave implements CommandArgument {

    @Override
    public void onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Fuck off console faggot");
            return;
        }
        Player p = (Player) sender;
        if (label.equalsIgnoreCase("team")) {
            if (args.length == 0) {
                Messages.TEAM_HELP(p);
            } else {
                if (args[0].equalsIgnoreCase("leave")) {
                    String team = TeamsYML.getTeams().getString("players." + p.getName() + ".team");
                    if (team != null) {
                        List<String> a = TeamsYML.getTeams().getStringList("teams." + team + ".members");
                        a.remove(p.getName());

                        if (a.size() == 0) {
                            TeamsYML.getTeams().set("teams." + team, null);
                            List<String> c = TeamsYML.getTeams().getStringList("CurrentTeams");
                            c.remove(team);
                            TeamsYML.getTeams().set("CurrentTeams", c);
                            Bukkit.broadcastMessage(ChatColor.AQUA + team + ChatColor.GRAY + " has been disbanded by " + p.getName());
                        } else {
                            TeamsYML.getTeams().set("teams." + team + ".members", a);
                            TeamsYML.getTeams().set("teams." + team + ".invitedplayers", a);
                            for (String s : a) {
                                if (Bukkit.getPlayerExact(s) != null) {
                                    Player p2 = Bukkit.getPlayerExact(s);
                                    p2.sendMessage(ChatColor.DARK_AQUA + p.getDisplayName() + ChatColor.GRAY + "has left the team!");
                                }
                            }
                        }

                        TeamsYML.getTeams().set("players." + p.getName() + ".team", null);
                        List<String> b = TeamsYML.getTeams().getStringList("PlayersInTeams");
                        b.remove(p.getName());
                        TeamsYML.getTeams().set("PlayersInTeams", b);
                        TeamsYML.saveTeams();

                        p.sendMessage(ChatColor.GRAY + "You left the team!");

                    } else Messages.NOT_IN_TEAM(p);
                }
            }
        }
        return;
    }

}
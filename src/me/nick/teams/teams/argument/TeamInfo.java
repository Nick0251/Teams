package me.nick.teams.teams.argument;

import me.nick.teams.Main;
import me.nick.teams.commands.CommandArgument;
import me.nick.teams.messages.Messages;
import me.nick.teams.teams.TeamsYML;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeamInfo implements CommandArgument {

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
                if (args[0].equalsIgnoreCase("info")) {
                    String team = TeamsYML.getTeams().getString("players." + p.getName() + ".team");
                    String creator = TeamsYML.getTeams().getString("teams." + team + ".leader");
                    if (team != null) {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&m--------------------------------"));
                        p.sendMessage(ChatColor.AQUA + "Team: " + team);
                        p.sendMessage(ChatColor.AQUA + "Leader: " + creator);
                        p.sendMessage(ChatColor.AQUA + "Members: ");
                        for (String members : TeamsYML.getTeams().getStringList("teams." + team + ".members")) {
                            p.sendMessage(ChatColor.AQUA + members);
                        }
                        p.sendMessage(ChatColor.AQUA + "Invited Players: ");
                        for (String invitedplayers : TeamsYML.getTeams().getStringList("teams." + team + ".invitedplayers")) {
                            p.sendMessage(ChatColor.AQUA + invitedplayers);
                        }
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&m--------------------------------"));
                    } else Messages.NOT_IN_TEAM(p);
                }
            }
        }

        return;
    }

}
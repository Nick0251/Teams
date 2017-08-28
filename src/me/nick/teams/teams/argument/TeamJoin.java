package me.nick.teams.teams.argument;

import me.nick.teams.commands.CommandArgument;
import me.nick.teams.messages.Messages;
import me.nick.teams.teams.TeamsYML;
import mkremins.fanciful.FancyMessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TeamJoin implements CommandArgument {

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
                if (args[0].equalsIgnoreCase("join")) {
                    Player target = Bukkit.getServer().getPlayer(args[1]);
                    String t = target.getName();
                    String targetteam = TeamsYML.getTeams().getString("players." + t + ".team");
                    List<String> a = TeamsYML.getTeams().getStringList("teams." + args[1] + ".invitedplayers");
                    List<String> b = TeamsYML.getTeams().getStringList("teams." + args[1] + ".members");
                    List<String> c = TeamsYML.getTeams().getStringList("CurrentTeams");
                    List<String> d = TeamsYML.getTeams().getStringList("PlayersInTeams");
                    String team = TeamsYML.getTeams().getString("players." + p.getName() + ".team");
                    if (args[1] == t) {
                        args[1] = args[1].replace(t, targetteam);
                    }
                        if (c.contains(args[1])) {
                            TeamsYML.getTeams().set("players." + p.getName() + ".team", args[1]);
                            a.remove(p.getName());
                            TeamsYML.getTeams().set("teams." + args[1] + ".invitedplayers", a);
                            b.add(p.getName());
                            TeamsYML.getTeams().set("teams." + args[1] + ".members", b);
                            d.add(p.getName());
                            TeamsYML.getTeams().set("PlayersInTeams", d);
                            TeamsYML.saveTeams();
                            p.sendMessage(ChatColor.AQUA + "You " + ChatColor.GRAY + "have successfully joined " + ChatColor.GREEN + args[1]);
                        } else {
                        Messages.TEAM_NOT_EXISTING(p);
                        }
                }
            }
        }
        return;
    }
}

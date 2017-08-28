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

public class TeamUnInvite implements CommandArgument {

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
                if (args[0].equalsIgnoreCase("uninvite")) {
                    String team = TeamsYML.getTeams().getString("players." + p.getName() + ".team");
                    String leader = TeamsYML.getTeams().getString("teams." + team + ".leader");
                    Player target = Bukkit.getServer().getPlayer(args[1]);
                    List<String> invitedplayers = new ArrayList();
                    if (team != null) {
                        if (invitedplayers.contains(target.getName())) {
                                    invitedplayers.add(args[1]);
                                    TeamsYML.getTeams().set("teams." + team + ".invitedplayers", invitedplayers);
                                    TeamsYML.saveTeams();
                                    p.sendMessage(ChatColor.AQUA + "You " + ChatColor.GRAY + "have successfully un-invited " + ChatColor.GREEN + target.getName());

                        } else Messages.TARGET_ALREADY_INVITED(p);
                    } else Messages.NOT_IN_TEAM(p);
                }
            }
        }

        return;
    }

}
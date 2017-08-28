package me.nick.teams.teams.argument;

import me.nick.teams.Main;
import me.nick.teams.commands.CommandArgument;
import me.nick.teams.events.Chat;
import me.nick.teams.messages.Messages;
import me.nick.teams.teams.TeamsYML;
import net.minecraft.server.v1_8_R1.CommandNumber;
import org.apache.logging.log4j.message.Message;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

import static me.nick.teams.teams.TeamsYML.team;

public class TeamCreate implements CommandArgument {

    @Override
    public void onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Fuck off console faggot");
            return;
        }
        Player p = (Player) sender;
        List<String> c = TeamsYML.getTeams().getStringList("CurrentTeams");
        if (label.equalsIgnoreCase("team")) {
            if (args.length == 0 || args.length == 1) {
                Messages.TEAM_HELP(p);
            } else {
                if (args[0].equalsIgnoreCase("create")) {
                    if (TeamsYML.getTeams().getStringList("PlayersInTeams").contains(p.getName())) {
                        Messages.ALREADY_IN_TEAM(p);
                    } else {
                        for (String teamname : c) {
                            if (teamname.equalsIgnoreCase(args[1])) {
                                Messages.TEAM_NAME_USED(p);
                                return;
                            }
                            }
                                TeamsYML.getTeams().set("players." + p.getName() + ".team", args[1]);
                                TeamsYML.getTeams().set("teams." + args[1] + ".leader", p.getName());
                                List<String> a = new ArrayList();
                                a.add(p.getName());
                                TeamsYML.getTeams().set("teams." + args[1] + ".members", a);

                                List<String> PlayersInTeams = TeamsYML.getTeams().getStringList("PlayersInTeams");
                                PlayersInTeams.add(p.getName());
                                TeamsYML.getTeams().set("PlayersInTeams", PlayersInTeams);

                                String team = TeamsYML.getTeams().getString("players." + p.getName() + ".team");
                                List<String> CurrentTeams = TeamsYML.getTeams().getStringList("CurrentTeams");
                                CurrentTeams.add(args[1]);
                                TeamsYML.getTeams().set("CurrentTeams", CurrentTeams);
                                TeamsYML.saveTeams();

                                Bukkit.broadcastMessage(ChatColor.AQUA + TeamsYML.getTeams().getString("players." + p.getName() + ".team") + ChatColor.GRAY + " has been created by " + ChatColor.GREEN + p.getDisplayName());
                            }
                        }
                    }
                } return;
            }


        }
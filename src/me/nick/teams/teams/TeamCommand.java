package me.nick.teams.teams;

import me.nick.teams.commands.CommandArgument;
import me.nick.teams.messages.Messages;
import me.nick.teams.teams.argument.*;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class TeamCommand implements CommandExecutor {

    private Map<String, CommandArgument> arguments;

    public TeamCommand() {
        this.arguments = new HashMap<>();
        this.arguments.put("create", new TeamCreate());
        this.arguments.put("leave", new TeamLeave());
        this.arguments.put("info", new TeamInfo());
        this.arguments.put("invite", new TeamInvite());
        this.arguments.put("join", new TeamJoin());
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("team") || cmd.getName().equalsIgnoreCase("teams") || cmd.getName().equalsIgnoreCase("t"))
            if (!(sender instanceof Player)) {
                sender.sendMessage("Fuck off console faggot");
            } else {
                Player p = (Player) sender;
                if (args.length == 0) {
                    Messages.TEAM_HELP(p);
                    return true;
                }

                if (this.arguments.containsKey(args[0].toLowerCase())) {
                    this.arguments.get(args[0].toLowerCase()).onCommand(sender, cmd, label, args);
                } else {
                    Messages.TEAM_HELP(p);
                    return true;
                }
            }
        return true;
    }

}
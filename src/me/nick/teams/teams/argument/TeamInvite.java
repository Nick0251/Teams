package me.nick.teams.teams.argument;

import me.nick.teams.commands.CommandArgument;
import me.nick.teams.events.Chat;
import me.nick.teams.messages.Messages;
import mkremins.fanciful.FancyMessage;
import me.nick.teams.teams.TeamsYML;
import net.minecraft.server.v1_8_R1.ChatSerializer;
import net.minecraft.server.v1_8_R1.PacketPlayOutChat;
import net.minecraft.server.v1_8_R1.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Bukkit.getPlayerExact;
import static org.bukkit.Bukkit.getServer;
import static sun.audio.AudioPlayer.player;

public class TeamInvite implements CommandArgument {

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
                if (args[0].equalsIgnoreCase("invite")) {
                    String team = TeamsYML.getTeams().getString("players." + p.getName() + ".team");
                    String leader = TeamsYML.getTeams().getString("teams." + team + ".leader");
                    Player target = Bukkit.getServer().getPlayer(args[1]);
                    Player t = (Player) target;
                    String targetteam = TeamsYML.getTeams().getString("players." + target + ".team");
                    List<String> invitedplayers = new ArrayList();
                    if (team != null) {
                        if (!(invitedplayers.contains(target.getName()))) {
                            if (target != null) {
                                if (targetteam == null) {
                                    invitedplayers.add(args[1]);
                                    TeamsYML.getTeams().set("teams." + team + ".invitedplayers", invitedplayers);
                                    TeamsYML.saveTeams();
                                    p.sendMessage(ChatColor.AQUA + "You " + ChatColor.GRAY + "have successfully invited " + ChatColor.GREEN + target.getName());

                                    t.sendMessage(ChatColor.GRAY + "You've been invited to join " + ChatColor.AQUA + team);
                                    new FancyMessage("Click here to join!")
                                            .color(ChatColor.AQUA)
                                            .tooltip(ChatColor.AQUA + "Click here")
                                            .command("/team join " + team)
                                            .send(t);

                                } else Messages.PLAYER_IN_TEAM(p);
                            } else Messages.PLAYER_NOT_ONLINE(p);
                        } else Messages.TARGET_ALREADY_INVITED(p);
                    } else Messages.NOT_IN_TEAM(p);
                }
            }
        }

        return;
    }

}
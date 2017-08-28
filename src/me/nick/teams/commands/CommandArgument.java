package me.nick.teams.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public interface CommandArgument {

    void onCommand(CommandSender sender, Command command, String label, String[] args);

}
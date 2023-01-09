package com.saryion.SaryionHomes.commands;

import com.saryion.SaryionHomes.homes.Homes;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSetHome extends Command {
    public CommandSetHome() {
        super("sethome", "/sethome <home>", true);
    }

    @Override
    public boolean onCommand(CommandSender sender, String[] args) {
        var player = (Player)sender;
        if (args.length == 0 || args[0].isBlank()) {
            sendUsage(player);
            return true;
        }

        var homes = Homes.getHomes(player);
        var homeName = args[0];

        if (homes.getHome(homeName) != null) {
            send(player, "&cYou already have a home named " + homeName + ".");
            return true;
        }

        homes.addHome(homeName, player.getLocation());
        return true;
    }
}

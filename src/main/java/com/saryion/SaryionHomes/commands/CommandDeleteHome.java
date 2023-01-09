package com.saryion.SaryionHomes.commands;

import com.saryion.SaryionHomes.homes.Homes;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public final class CommandDeleteHome extends Command {
    public CommandDeleteHome() {
        super("delhome", "/delhome <home>", true);
    }

    @Override
    public boolean onCommand(CommandSender sender, String[] args) {
        var player = (Player)sender;

        if (args.length == 0 || args[0].isBlank()) {
            sendUsage(player);
            return true;
        }

        var homes = Homes.getHomes(player);
        var home = homes.getHome(args[0]);
        if (home == null) {
            send(player, "You don't have a home called " + args[0] + ".");
            return true;
        }

        homes.removeHome(args[0]);
        send(player, "Home named " + args[0] + " has been deleted.");
        return true;
    }
}

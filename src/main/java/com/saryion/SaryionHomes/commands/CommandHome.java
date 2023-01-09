package com.saryion.SaryionHomes.commands;

import com.saryion.SaryionHomes.homes.Homes;
import com.saryion.SaryionHomes.gui.HomeGUI;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public final class CommandHome extends Command {
    public CommandHome() {
        super("home", "/home", true);
    }

    @Override
    public boolean onCommand(CommandSender sender, String[] args) {
        var player = (Player)sender;
        var homes = Homes.getHomes(player);

        if (args.length == 0) {
            new HomeGUI(player, homes);
            return true;
        }

        var home = homes.getHome(args[0]);
        if (home == null) {
            send(player, "&cYou don't have a home with name " + args[0] + ".");
            return true;
        }

        player.teleport(home.getLocation());
        send(player, "You teleported to your home (" + home.getName() + ").");
        return true;
    }
}

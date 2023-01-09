package com.saryion.SaryionHomes.commands;

import com.saryion.SaryionHomes.Homes;
import com.saryion.SaryionHomes.gui.HomeGUI;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class CommandHome extends Command {
    private final Plugin plugin;

    public CommandHome(Plugin plugin) {
        super("home", "/home", true);
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, String[] args) {
        var player = (Player)sender;
        var homes = Homes.getHomes(player);

        if (args.length == 0) {
            new HomeGUI(player, homes, plugin);
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

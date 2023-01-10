package com.saryion.SaryionHomes.commands;

import com.saryion.SaryionHomes.handlers.HomeHandler;
import com.saryion.SaryionHomes.util.Home;
import com.saryion.SaryionHomes.util.Lang;

import org.bukkit.Material;
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

        var homeName = args[0];
        if (HomeHandler.hasHome(player, homeName)) {
            send(player, Lang.HOME_EXISTS, Lang.PREFIX);
            return true;
        }

        var home = new Home(homeName, player.getLocation(), Material.GRASS_BLOCK, player);
        HomeHandler.addHome(player, home);
        return true;
    }
}

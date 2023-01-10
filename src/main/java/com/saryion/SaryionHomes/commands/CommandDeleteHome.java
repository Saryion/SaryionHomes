package com.saryion.SaryionHomes.commands;

import com.saryion.SaryionHomes.handlers.HomeHandler;
import com.saryion.SaryionHomes.util.Lang;

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

        var home = HomeHandler.getHome(player, args[0]);
        if (home == null) {
            send(player, Lang.HOME_NONE, Lang.PREFIX);
            return true;
        }

        HomeHandler.removeHome(player, args[0]);
        send(player, Lang.HOME_DELETED, Lang.PREFIX);
        return true;
    }
}

package com.saryion.SaryionHomes.commands;

import com.saryion.SaryionHomes.handlers.HomeHandler;
import com.saryion.SaryionHomes.util.Lang;
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

        if (args.length == 0) {
            new HomeGUI(player);
            return true;
        }

        var home = HomeHandler.getHome(player, args[0]);
        if (home == null) {
            send(player, Lang.HOME_NONE, Lang.PREFIX);
            return true;
        }

        home.teleport(player);
        send(player, Lang.HOME_TELEPORTED, Lang.PREFIX);
        return true;
    }
}

package com.saryion.SaryionHomes.handlers;

import com.saryion.SaryionHomes.SaryionHomes;
import com.saryion.SaryionHomes.interfaces.IHome;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public abstract class HomeHandler {
    public static ArrayList<IHome> getHomes(Player player) {
        return SaryionHomes.homeCache.get(player.getUniqueId().toString());
    }

    public static IHome getHome(Player player, String name) {
        var homes = SaryionHomes.homeCache.get(player.getUniqueId().toString());
        if (homes.isEmpty()) return null;

        for (var home : homes) {
            if (!home.getName().equals(name)) continue;

            return home;
        }

        return null;
    }

    public static void addHome(Player player, IHome home) {
        getHomes(player).add(home);
    }

    public static void removeHome(Player player, String name) {
        var homes = getHomes(player);

        for (var i = 0; i < homes.size(); i++) {
            if (!homes.get(i).getName().equalsIgnoreCase(name)) continue;

            homes.remove(i);
            break;
        }
    }

    public static boolean hasHome(Player player, String name) {
        return getHome(player, name) != null;
    }
}

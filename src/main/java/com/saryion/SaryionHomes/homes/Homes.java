package com.saryion.SaryionHomes.homes;

import com.saryion.SaryionHomes.SaryionHomes;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Homes {
    private final String uuid;
    private ArrayList<Home> homes = new ArrayList<>();

    public Homes(Player player) {
        this.uuid = player.getUniqueId().toString();
    }

    public Homes(Player player, ArrayList<Home> homes) {
        this(player);
        this.homes = homes;
    }

    public ArrayList<Home> getHomes() {
        return this.homes;
    }

    public static Homes getHomes(Player player) {
        return SaryionHomes.homeCache.get(player.getUniqueId().toString());
    }

    public Home getHome(String name) {
        for (var home : this.homes) {
            if (!home.getName().equalsIgnoreCase(name)) continue;
            return home;
        }

        return null;
    }

    public void addHome(Home home) {
        this.homes.add(home);
    }

    public void addHome(String name, Location location) {
        this.addHome(new Home(name, location));
    }

    public void removeHome(String name) {
        for (var i = 0; i < this.homes.size(); i++) {
            if (!homes.get(i).getName().equalsIgnoreCase(name)) continue;

            this.homes.remove(i);
            break;
        }
    }

    public String getUUID() {
        return uuid;
    }

    public static boolean hasHomes(Player player) {
        var homes = SaryionHomes.homeCache.get(player.getUniqueId().toString());
        return homes != null;
    }
}

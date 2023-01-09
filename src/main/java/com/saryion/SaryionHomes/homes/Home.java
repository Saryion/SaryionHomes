package com.saryion.SaryionHomes.homes;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Home {
    private Player player;
    private String name;
    private Location location;
    private Material icon = Material.GRASS_BLOCK;

    public Home(String name, Location location) {
        setName(name);
        setLocation(location);
    }

    public Home(String name, Location location, Material icon) {
        this(name, location);
        setIcon(icon);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.isBlank()) return;
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    private void setLocation(Location location) {
        this.location = location;
    }

    public Material getIcon() {
        return icon;
    }

    public void setIcon(Material icon) {
        this.icon = icon;
    }

    public Player getOwner() {
        return this.player;
    }

    public void gotoHouse(Player player) {
        player.teleport(this.location);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof String)) return false;

        return this.name.equals(obj);
    }
}

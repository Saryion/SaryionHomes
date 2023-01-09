package com.saryion.SaryionHomes;

import org.bukkit.Location;
import org.bukkit.Material;

public class Home {
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

    public void setLocation(Location location) {
        this.location = location;
    }

    public Material getIcon() {
        return icon;
    }

    public void setIcon(Material icon) {
        this.icon = icon;
    }
}

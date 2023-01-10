package com.saryion.SaryionHomes.util;

import com.saryion.SaryionHomes.interfaces.IHome;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Home implements IHome {
    private final String uuid;
    private String name;
    private Location location;
    private Material icon;

    public Home(String name, Location location, Player player) {
        this.uuid = player.getUniqueId().toString();
        setName(name);
        setLocation(location);
    }

    public Home(String name, Location location, Material icon, Player player) {
        this(name, location, player);
        this.setIcon(icon);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        if (name.isBlank()) throw new IllegalArgumentException();
        this.name = name;
    }

    @Override
    public Location getLocation() {
        return this.location;
    }

    @Override
    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public Material getIcon() {
        return this.icon;
    }

    @Override
    public void setIcon(Material material) {
        this.icon = material;
    }

    @Override
    public String getOwner() {
        return this.uuid;
    }

    @Override
    public void teleport(Player player) {
        player.teleport(this.location);
    }
}

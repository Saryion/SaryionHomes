package com.saryion.SaryionHomes.interfaces;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public interface IHome {
    String getName();

    void setName(String name);

    Location getLocation();

    void setLocation(Location location);

    Material getIcon();

    void setIcon(Material material);

    String getOwner();

    void teleport(Player player);
}

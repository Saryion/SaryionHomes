package com.saryion.SaryionHomes.listeners;

import com.saryion.SaryionHomes.Homes;
import com.saryion.SaryionHomes.SaryionHomes;
import com.saryion.SaryionHomes.gui.HomeGUI;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

public class HomeListener implements Listener {
    public HomeListener(Plugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void OnPlayerJoin(PlayerJoinEvent e) {
        var player = e.getPlayer();

        if (!Homes.hasHomes(player)) {
            SaryionHomes.homeCache.put(player.getUniqueId().toString(), new Homes(player));
        }
    }

    @EventHandler
    private void OnInventoryClick(InventoryClickEvent e) {
        e.setCancelled(true);
        var player = (Player)e.getWhoClicked();

        if (!e.getView().getTitle().contains("Homes")) return;

        var clickedItem = e.getCurrentItem();
        if (clickedItem == null) return;
        if (clickedItem.getType() == HomeGUI.borderMaterial) return;

        var houseName = clickedItem.getItemMeta().getDisplayName();
        var homes = Homes.getHomes(player);

        for (var home : homes.getHomes()) {
            if (!home.getName().equals(houseName)) continue;
            player.teleport(home.getLocation());
            player.sendMessage("You teleported to home " + houseName + ".");
        }

        e.getWhoClicked().closeInventory();
    }
}

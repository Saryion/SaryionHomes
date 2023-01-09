package com.saryion.SaryionHomes.listeners;

import com.saryion.SaryionHomes.homes.Homes;
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
    private void OnPlayerJoin(PlayerJoinEvent e) {
        var player = e.getPlayer();

        if (!Homes.hasHomes(player)) {
            SaryionHomes.homeCache.put(player.getUniqueId().toString(), new Homes(player));
        }
    }

    @EventHandler
    private void OnInventoryClick(InventoryClickEvent e) {
        var player = (Player)e.getWhoClicked();

        if (!e.getView().getTitle().equals(player.getName() + "'s Homes")) return;
        e.setCancelled(true);

        var clickedInventory = e.getClickedInventory();
        if (e.getView().getBottomInventory() == clickedInventory) return;

        var clickedItem = e.getCurrentItem();
        if (clickedItem == null || clickedItem.getType() == HomeGUI.borderMaterial) return;

        var houseName = clickedItem.getItemMeta().getDisplayName();
        var home = Homes.getHomes(player).getHome(houseName);

        if (home == null) return;
        home.gotoHouse(player);

        player.sendMessage("You teleported to home " + houseName + ".");
        e.getWhoClicked().closeInventory();
    }
}

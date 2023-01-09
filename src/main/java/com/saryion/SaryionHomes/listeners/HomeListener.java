package com.saryion.SaryionHomes.listeners;

import com.saryion.SaryionHomes.Homes;
import com.saryion.SaryionHomes.SaryionHomes;
import com.saryion.SaryionHomes.gui.HomeGUI;
import org.bukkit.Bukkit;
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

        if (!e.getView().getTitle().contains("Homes")) return;
        if (e.getCurrentItem() == null) return;
        if (e.getCurrentItem().getType() == HomeGUI.borderMaterial) return;

        var houseName = e.getCurrentItem().getItemMeta().getDisplayName();
        var homes = SaryionHomes.homeCache.get(e.getWhoClicked().getUniqueId().toString());

        for (var home : homes.getHomes()) {
            if (!home.getName().equals(houseName)) continue;
            e.getWhoClicked().teleport(home.getLocation());
            e.getWhoClicked().sendMessage("You teleported to home " + houseName + ".");
        }

        e.getWhoClicked().closeInventory();
    }
}

package com.saryion.SaryionHomes.listeners;

import com.saryion.SaryionHomes.handlers.HomeHandler;
import com.saryion.SaryionHomes.util.Lang;
import com.saryion.SaryionHomes.SaryionHomes;
import com.saryion.SaryionHomes.gui.HomeGUI;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

public final class LHome implements Listener {
    public LHome(Plugin plugin) {
        SaryionHomes.instance.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    private void OnPlayerJoin(PlayerJoinEvent e) {
        var player = e.getPlayer();

        if (HomeHandler.getHomes(player) == null) {
            SaryionHomes.homeCache.put(player.getUniqueId().toString(), new ArrayList<>());
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
        var home = HomeHandler.getHome(player, houseName);

        if (home == null) return;
        home.teleport(player);

        player.sendMessage(Lang.HOME_TELEPORTED);
        e.getWhoClicked().closeInventory();
    }
}

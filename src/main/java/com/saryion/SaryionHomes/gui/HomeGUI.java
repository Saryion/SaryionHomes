package com.saryion.SaryionHomes.gui;

import com.saryion.SaryionHomes.Home;
import com.saryion.SaryionHomes.Homes;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class HomeGUI {
    private final Player player;
    private final ArrayList<Home> homes;
    private final String inventoryName;
    public final static Material borderMaterial = Material.BLACK_STAINED_GLASS_PANE;

    public HomeGUI(Player player, Homes homes, Plugin plugin) {
        this.player = player;
        this.homes = homes.getHomes();
        this.inventoryName = player.getName() + "'s Homes";
        this.setup();
    }

    private void setup() {
        var inventory = Bukkit.createInventory(player, 9 * 6, inventoryName);
        makeBorder(borderMaterial, inventory);
        populate(inventory);

        player.openInventory(inventory);
    }

    private void populate(Inventory inventory) {
        var homes = this.homes;

        for (var home : homes) {
            for (var i = 0; i < inventory.getSize(); i++) {
                if (inventory.getItem(i) != null) continue;

                inventory.setItem(i, buildItem(home.getIcon(), home.getName()));
                break;
            }
        }
    }

    private ItemStack buildItem(Material material, String name, String... lore) {
        var item = new ItemStack(material);
        var itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(name);

        if (lore.length != 0 && (lore.length == 1 && !lore[0].isEmpty())) {
            List<String> itemLore = new ArrayList<>();
            for (var l : lore) {
                itemLore.add(l);
            }

            itemMeta.setLore(itemLore);
        }

        item.setItemMeta(itemMeta);
        return item;
    }

    private ItemStack buildItem(Material material, String name) {
        return this.buildItem(material, name, "");
    }

    private void makeBorder(Material material, Inventory inventory) {
        var inventorySize = inventory.getSize();
        if ((inventorySize / 9) < 3) return;

        var rows = inventorySize / 9;

        for (var i = 0; i < inventorySize; i++) {
            var row = i / 9;
            var column = (i % 9) + 1;

            if (row == 0 || row == rows - 1 || column == 1 || column == 9) {
                inventory.setItem(i, buildItem(material, " "));
            }
        }
    }

    private boolean isBorder(ItemStack item) {
        return item.getType() == borderMaterial;
    }

    private boolean isBorder(Material material) {
        return material == borderMaterial;
    }
}

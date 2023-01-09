package com.saryion.SaryionHomes.gui;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


public abstract class GUI {
    public static void makeBorder(Material material, Inventory inventory) {
        var inventorySize = inventory.getSize();
        if ((inventorySize / 9) < 3) return;

        var rows = inventorySize / 9;
        var border = new ItemStack(material);

        try {
            var meta = border.getItemMeta();
            meta.setDisplayName(" ");
            border.setItemMeta(meta);
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }

        for (var i = 0; i < inventorySize; i++) {
            var row = i / 9;
            var column = (i % 9) + 1;

            if (row == 0 || row == rows - 1 || column == 1 || column == 9) {
                inventory.setItem(i, border);
            }
        }
    }
}

package com.saryion.SaryionHomes;

import com.saryion.SaryionHomes.commands.CommandDeleteHome;
import com.saryion.SaryionHomes.commands.CommandHome;
import com.saryion.SaryionHomes.commands.CommandSetHome;
import com.saryion.SaryionHomes.homes.Homes;
import com.saryion.SaryionHomes.listeners.HomeListener;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class SaryionHomes extends JavaPlugin {
    public static HashMap<String, Homes> homeCache = new HashMap();

    @Override
    public void onEnable() {
        new CommandHome(this);
        new CommandSetHome();
        new CommandDeleteHome();

        new HomeListener(this);
    }
}

package com.saryion.SaryionHomes;

import com.saryion.SaryionHomes.commands.CommandDeleteHome;
import com.saryion.SaryionHomes.commands.CommandHome;
import com.saryion.SaryionHomes.commands.CommandSetHome;
import com.saryion.SaryionHomes.interfaces.IHome;
import com.saryion.SaryionHomes.listeners.LHome;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;

public class SaryionHomes extends JavaPlugin {
    public static SaryionHomes instance;

    public static HashMap<String, ArrayList<IHome>> homeCache = new HashMap();

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        new CommandHome();
        new CommandSetHome();
        new CommandDeleteHome();

        new LHome(this);
    }
}

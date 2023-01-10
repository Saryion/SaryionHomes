package com.saryion.SaryionHomes.util;

import com.saryion.SaryionHomes.SaryionHomes;
import org.bukkit.configuration.file.FileConfiguration;

public abstract class Lang {
    public final static FileConfiguration config = SaryionHomes.instance.getConfig();

    public final static String PREFIX = config.getString("long.prefix");
    public final static String HOME_TELEPORTED = config.getString("long.home-teleported");
    public final static String HOME_NONE = config.getString("long.home-none");
    public final static String HOME_DELETED = config.getString("long.home-deleted");
    public final static String HOME_EXISTS = config.getString("long.home-exists");
}

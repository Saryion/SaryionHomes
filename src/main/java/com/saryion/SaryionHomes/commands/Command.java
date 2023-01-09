package com.saryion.SaryionHomes.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

public abstract class Command extends BukkitCommand implements CommandExecutor {
    private final String cmd;
    private final String usage;
    private boolean isPlayer;

    public Command(String cmd, String usage) {
        super(cmd);
        this.cmd = cmd;
        this.usage = usage;
        this.setup();
    }

    public Command(String cmd, String usage, boolean isPlayer) {
        this(cmd, usage);
        this.isPlayer = isPlayer;
    }

    public void setup() {
        Bukkit.getPluginCommand(this.cmd).setExecutor(this);
    }


    public void send(CommandSender sender, String msg) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
    }

    public void send(CommandSender sender, String msg, String prefix) {
        this.send(sender, prefix + msg);
    }

    @Override
    public String getUsage() {
        return this.usage;
    }

    public void sendUsage(CommandSender sender) {
        this.send(sender, this.getUsage());
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        return true;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        if (isPlayer && !(commandSender instanceof Player)) {
            this.send(commandSender, "&cOnly players can use this command.");
            return true;
        }

        this.onCommand(commandSender, strings);
        return true;
    }

    public abstract boolean onCommand(CommandSender sender, String[] args);
}

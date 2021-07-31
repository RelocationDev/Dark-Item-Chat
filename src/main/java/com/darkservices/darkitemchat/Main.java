package com.darkservices.darkitemchat;

import com.darkservices.darkitemchat.listener.ChatListener;
import com.darkservices.darkitemchat.util.Methods;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(new ChatListener(this, new Methods()), this);
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lDark&f&lItemChat » &7DarkItemChat has enabled!"));
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lDark&f&lItemChat » &7DarkItemChat has disabled!"));
    }

}

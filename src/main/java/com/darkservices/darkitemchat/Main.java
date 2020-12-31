package com.darkservices.darkitemchat;

import com.darkservices.darkitemchat.listener.ChatListener;
import com.darkservices.darkitemchat.util.Methods;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {


    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new ChatListener(this, new Methods()), this);
        getConfig().options().copyDefaults(true);
        saveConfig();
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lDark&f&lItemChat » &7DarkItemChat is enabled!"));
    }
}

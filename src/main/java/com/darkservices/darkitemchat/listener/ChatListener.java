package com.darkservices.darkitemchat.listener;

import com.darkservices.darkitemchat.Main;
import com.darkservices.darkitemchat.util.Methods;
import com.darkservices.darkitemchat.util.ReflectionUtil;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

public class ChatListener implements Listener {

    private Main plugin;
    private Methods methods;

    public ChatListener(Main plugin, Methods methods) {
        this.plugin = plugin;
        this.methods = methods;
    }


    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onChat(AsyncPlayerChatEvent e) {
        String msg = e.getMessage();
        Player p = e.getPlayer();
        if (plugin.getConfig().getBoolean("permission.enabled") && !p.hasPermission(plugin.getConfig().getString("permission.node"))) {
            for (String s : plugin.getConfig().getStringList("permission.denied")) {
                e.getPlayer().sendMessage(methods.color(s));
                return;
            }
        }
        if (!msg.equals(plugin.getConfig().getString("item-chat-message"))) {
            return;
        }
        e.setCancelled(true);
        ItemStack item = p.getItemInHand();
        String itemName;
        if (p.getItemInHand() == null) {
            itemName = "&cHand";
        }
        if (p.getItemInHand().getItemMeta().getDisplayName() == null || p.getItemInHand().getItemMeta() == null) {
            itemName = p.getItemInHand().getType().toString().toLowerCase(Locale.ENGLISH).replace('_', ' ');;
        } else {
            itemName = p.getItemInHand().getItemMeta().getDisplayName();
        }
        for (Player online : Bukkit.getOnlinePlayers()) {
            methods.sendItemTooltipMessage(online, itemName, item);
        }
    }
}

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

    private final Main plugin;
    private final Methods methods;

    public ChatListener(Main plugin, Methods methods) {
        this.plugin = plugin;
        this.methods = methods;
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onChat(AsyncPlayerChatEvent event) {

        Player player = event.getPlayer();
        String msg = event.getMessage();

        if (plugin.getConfig().getBoolean("permission.enabled") && !player.hasPermission(plugin.getConfig().getString("permission.node"))) {
            plugin.getConfig().getStringList("permission.denied").forEach(s -> player.sendMessage(methods.color(s)));
            return;
        }

        if (!plugin.getConfig().getString("item-chat-message").contains(msg.toLowerCase())) return;

        event.setCancelled(true);

        ItemStack item = player.getItemInHand();
        String itemName;

        if (player.getItemInHand() == null) {
            Bukkit.getOnlinePlayers().forEach(online -> methods.sendItemTooltipMessage(online, "&cHand", item));
            return;
        }

        if (item.getItemMeta().getDisplayName() == null || item.getItemMeta() == null) {
            itemName = player.getItemInHand().getType().toString().toLowerCase(Locale.ENGLISH).replace('_', ' ');;
            Bukkit.getOnlinePlayers().forEach(online -> methods.sendItemTooltipMessage(online, itemName, item));
            return;
        }

        itemName = player.getItemInHand().getItemMeta().getDisplayName();
        Bukkit.getOnlinePlayers().forEach(online -> methods.sendItemTooltipMessage(online, itemName, item));

    }

}

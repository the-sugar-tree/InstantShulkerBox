package com.sugar_tree.instantshulkerbox;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class InstantShulkerBox extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(new Listeners(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}

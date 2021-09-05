package org.hinoob;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.hinoob.commands.ParkourCommand;
import org.hinoob.listener.ParkourListener;
import org.hinoob.manager.ConfigManager;
import org.hinoob.manager.ParkourManager;

import java.io.File;

public class ParkourPlugin extends JavaPlugin {


    public static ParkourPlugin INSTANCE;

    public ConfigManager configManager;
    public ParkourManager parkourManager;

    @Override
    public void onEnable(){
        INSTANCE = this;
        this.saveDefaultConfig();

        getLogger().info("Successfully enabled!");

        // Set the available managers
        configManager = new ConfigManager();
        configManager.setConfigFile(new File(this.getDataFolder(), "config.yml"));

        parkourManager = new ParkourManager();

        // Register all the events & commands
        this.getServer().getPluginManager().registerEvents(new ParkourListener(), this);
        getCommand("parkour").setExecutor(new ParkourCommand());
    }
}

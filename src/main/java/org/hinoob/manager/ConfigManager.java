package org.hinoob.manager;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.hinoob.ParkourPlugin;

import java.io.File;
import java.util.List;

public class ConfigManager {

    private File file;
    private FileConfiguration fileConfiguration;

    public void setConfigFile(File file){
        this.file = file;

        if(!file.exists()){
            try {
                boolean created = file.createNewFile();

                if(!created){
                    ParkourPlugin.INSTANCE.getLogger().severe("Error while trying to create the config file!");
                }
            }catch(Exception ignored){

            }
        }

        fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    public int getInteger(String key){
        return fileConfiguration.getInt(key);
    }

    public double getDouble(String key){
        return fileConfiguration.getDouble(key);
    }

    public String getString(String key){
        return fileConfiguration.getString(key);
    }

    public List<String> getStringList(String key){ return fileConfiguration.getStringList(key); }
}

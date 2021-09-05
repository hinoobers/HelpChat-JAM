package org.hinoob.manager;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.hinoob.ParkourPlugin;
import org.hinoob.data.PlayerData;
import org.hinoob.utils.RandomUtils;

public class ParkourManager {

    public void startParkour(Player player){
        PlayerData data = PlayerDataManager.getPlayerData(player);

        if(data.isParkourEnabled()) return; // It's already enabled

        data.setParkourEnabled(true);
        data.setStartPosition(player.getLocation());

        // Let's generate a start position

        double minX = ParkourPlugin.INSTANCE.configManager.getDouble("parkour-loc.max-x");
        double maxX = ParkourPlugin.INSTANCE.configManager.getDouble("parkour-loc.max-x");

        double minY = ParkourPlugin.INSTANCE.configManager.getDouble("parkour-loc.max-y");
        double maxY = ParkourPlugin.INSTANCE.configManager.getDouble("parkour-loc.max-y");

        double minZ = ParkourPlugin.INSTANCE.configManager.getDouble("parkour-loc.max-z");
        double maxZ = ParkourPlugin.INSTANCE.configManager.getDouble("parkour-loc.max-z");

        String world = ParkourPlugin.INSTANCE.configManager.getString("parkour-loc.world");
        World bukkitWorld;

        if(Bukkit.getWorld(world) == null){
            // Not found, let's create it
            bukkitWorld = Bukkit.createWorld(new WorldCreator(world));
        }else{
            bukkitWorld = Bukkit.getWorld(world);
        }

        double x = RandomUtils.generateRandomNumberBetween(minX, maxX);
        double y = RandomUtils.generateRandomNumberBetween(minY, maxY);
        double z = RandomUtils.generateRandomNumberBetween(minZ, maxZ);

        final Location location = new Location(bukkitWorld, x, y, z);
        location.clone().subtract(0,1,0).getBlock().setType(Material.GOLD_BLOCK);

        player.teleport(location);

        // Let's now generate the next block

        data.setNextBlockLocation(this.generateNextBlock(player.getLocation()));
    }

    public Location generateNextBlock(Location currentLocation){
        // Current location is the location of the PLAYER not the block

        double y;

        // We can't set the y too high, or too low, i'll just make it a 50/50

        if(RandomUtils.generateRandomNumberBetween(0,100) > 75){
            y = currentLocation.getY() - 1;
        }else if(RandomUtils.generateRandomNumberBetween(0,100) < 50){
            y = currentLocation.getY() - 2;
        }else{
            y = currentLocation.getY();
        }

        double x = currentLocation.getX(), z = currentLocation.getZ();

        if(RandomUtils.generateRandomNumberBetween(0,100) > 50){
            x += RandomUtils.generateRandomNumberBetween(2,4);
        }else{
            z += RandomUtils.generateRandomNumberBetween(2,4);
        }

        return new Location(currentLocation.getWorld(), x, y, z);


    }
}

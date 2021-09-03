package org.hinoob.data;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class PlayerData {

    private Player player;

    private boolean parkourEnabled = false;
    private Location startPosition, nextBlockLocation;


    public PlayerData(Player player){
        this.player = player;
    }

    // Getters & Setters

    public boolean isParkourEnabled(){
        return parkourEnabled;
    }

    public void setParkourEnabled(boolean b){
        this.parkourEnabled = b;
    }

    public void setStartPosition(Location location){
        this.startPosition = location;
    }

    public Location getStartPosition(){
        return startPosition;
    }

    public Location getNextBlockLocation(){
        return nextBlockLocation;
    }

    public void setNextBlockLocation(Location loc){
        if(loc.getBlock().getType() != Material.GOLD_BLOCK){
            loc.getBlock().setType(Material.GOLD_BLOCK);
        }
        this.nextBlockLocation = loc;
    }

    // Getters & Setters
}

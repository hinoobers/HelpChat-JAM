package org.hinoob.data;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerData {

    private Player player;

    private boolean parkourEnabled = false;
    private Location startPosition, nextBlockLocation;
    public List<Block> parkourBlocks = new ArrayList<>();


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
        if(loc.getBlock().getType() != Material.EMERALD_BLOCK){
            loc.getBlock().setType(Material.EMERALD_BLOCK);
        }
        this.nextBlockLocation = loc;

        if(parkourBlocks.stream().noneMatch(b -> b.getX() == loc.getX() && b.getY() == loc.getY() && b.getZ() == loc.getZ())){
            parkourBlocks.add(loc.getBlock());
        }
    }

    // Getters & Setters
}

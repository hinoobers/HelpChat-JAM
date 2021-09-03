package org.hinoob.listener;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.hinoob.ParkourPlugin;
import org.hinoob.data.PlayerData;
import org.hinoob.manager.PlayerDataManager;

public class ParkourListener implements Listener {

    @EventHandler
    public void onJoin(PlayerMoveEvent e){
        PlayerData data = PlayerDataManager.getPlayerData(e.getPlayer());

        if(e.getTo() == null) return;
        if(e.getFrom().distance(e.getTo()) == 0) return;
        if(!data.isParkourEnabled()) return;

        if(data.getNextBlockLocation().getX() == e.getTo().getX() && data.getNextBlockLocation().getZ() == e.getTo().getZ() && data.getNextBlockLocation().getY() == e.getTo().getY() - 1){
            if(e.getTo().clone().subtract(0,1,0).getBlock().getType() == Material.GOLD_BLOCK){
                e.getPlayer().sendMessage(ChatColor.GOLD + "+1");
                data.setNextBlockLocation(ParkourPlugin.INSTANCE.parkourManager.generateNextBlock(e.getPlayer().getLocation()));
            }
        }
    }
}

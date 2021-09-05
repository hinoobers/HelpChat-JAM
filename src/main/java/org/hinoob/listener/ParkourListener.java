package org.hinoob.listener;

import org.bukkit.Bukkit;
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

        if(data.getNextBlockLocation().distance(e.getTo()) <= 1.2){
            if(e.getTo().clone().subtract(0,1,0).getBlock().getType() == Material.EMERALD_BLOCK){
                e.getPlayer().sendMessage(ChatColor.GOLD + "+1");
                e.getTo().clone().subtract(0,1,0).getBlock().setType(Material.REDSTONE_BLOCK);
                data.setNextBlockLocation(ParkourPlugin.INSTANCE.parkourManager.generateNextBlock(e.getPlayer().getLocation()));

                for(String commandString : ParkourPlugin.INSTANCE.configManager.getStringList("commands.on-checkpoint")){
                    commandString = commandString.replaceAll("%player%", e.getPlayer().getName());

                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), commandString);
                }
            }
        }
    }
}

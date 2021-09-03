package org.hinoob.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.hinoob.ParkourPlugin;
import org.hinoob.data.PlayerData;
import org.hinoob.manager.PlayerDataManager;

public class ParkourCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;
            PlayerData data = PlayerDataManager.getPlayerData(player);

            if(player.hasPermission("parkour.use")){
                if(strings.length == 0){
                    player.sendMessage(ChatColor.RED + "/parkour start - start a new parkour");
                    player.sendMessage(ChatColor.RED + "/parkour end - end the current parkour");
                    player.sendMessage(ChatColor.RED + "/parkour help - show the command list");
                }else if(strings.length == 1){
                    if(strings[0].equalsIgnoreCase("start")){
                        if(data.isParkourEnabled()){
                            player.sendMessage(ChatColor.RED + "You already have a active parkour running!");
                        }else{
                            ParkourPlugin.INSTANCE.parkourManager.startParkour(player);
                            player.sendMessage(ChatColor.GREEN + "Parkour started");
                        }
                    }else if(strings[0].equalsIgnoreCase("end")) {
                        if(!data.isParkourEnabled()){
                            player.sendMessage(ChatColor.RED + "You don't have a active parkour running!");
                        }else{
                            data.setParkourEnabled(false);
                            player.teleport(data.getNextBlockLocation());
                            player.sendMessage(ChatColor.GREEN + "Parkour ended");
                        }
                    }else if(strings[0].equalsIgnoreCase("help")){
                        player.sendMessage(ChatColor.RED + "/parkour start - start a new parkour");
                        player.sendMessage(ChatColor.RED + "/parkour end - end the current parkour");
                        player.sendMessage(ChatColor.RED + "/parkour help - show the command list");
                    }else{
                        player.sendMessage(ChatColor.RED + "Invalid argument, maybe try /parkour help?");
                    }
                }else{
                    player.sendMessage(ChatColor.RED + "Invalid argument, maybe try /parkour help?");
                }
            }else{
                player.sendMessage(ChatColor.RED + "You're not allowed to do that!");
            }
        }else{
            System.out.println("[Parkour] Only players can do that!");
        }

        return true;
    }
}

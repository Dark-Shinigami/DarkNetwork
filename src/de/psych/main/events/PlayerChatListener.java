package de.psych.main.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import de.psych.main.Main;

/**
 * Created by ErebosTheDark 23.05.2016
 */

public class PlayerChatListener implements Listener{

    @EventHandler
    public void on(AsyncPlayerChatEvent e){
        if(e.getPlayer().hasPermission("dnetwork.staffchat")) {
            if (e.getMessage().startsWith("!")) {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (!p.hasPermission("dnetwork.staffchat")) continue;
                    p.sendMessage("§7[§cStaff§7] " + e.getPlayer().getDisplayName() + " §7» §a" + e.getMessage().replace(e.getMessage().charAt(0) + "", ""));
                    e.setCancelled(true);
                }
            }
        }
        if(Main.staff.contains(e.getPlayer())){
            for(Player p : Bukkit.getOnlinePlayers()){
                if(!p.hasPermission("dnetwork.staffchat"))continue;
                p.sendMessage("§7[§cStaff§7] " + e.getPlayer().getDisplayName() + " §7» §a" + e.getMessage());
                e.setCancelled(true);
            }
        }
        
        
        Player p = e.getPlayer();
        if(p.hasPermission("dnetwork.admin")){
        	e.setFormat("§4Admin §7| §4"+p.getName() + " §7» §c"+ e.getMessage());
        	return;
        } else if(p.hasPermission("dnetwork.mod")){
        	e.setFormat("§cMod §7| §c"+p.getName() + " §7» §7"+ e.getMessage());
        	return;
        } else if(p.hasPermission("dnetwork.dev")){
        	e.setFormat("§bDev §7| §b"+p.getName() + " §7» §7"+ e.getMessage());
        	return;
        }else if(p.hasPermission("dnetwork.builder")){
        	e.setFormat("§2Builder §7| §2"+p.getName() + " §7» §7"+ e.getMessage());
        	return;
        }
        
        
        
        else{
        	e.setFormat("§7"+p.getName() + " §7» "+ e.getMessage());
        }
        
        
        
    }
    
    
    
}
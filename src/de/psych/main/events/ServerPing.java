package de.psych.main.events;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import net.md_5.bungee.api.ChatColor;

public class ServerPing implements Listener {
	
	@EventHandler
	public void onInteract(ServerListPingEvent e){
		
		File file = new File("plugins//DarkNetwork//Motd.yml");
		File ordner = new File("plugins//DarkNetwork");
		
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		if(!(ordner.exists())){
			ordner.mkdir();
		}
		
		if(!(file.exists())){
			try {
				file.createNewFile();
				cfg.set("Motd", "&3DarkException");
				cfg.save(file);
			} catch (IOException d) {
			
				d.printStackTrace();
			}
					
		}
		
		e.setMotd(ChatColor.translateAlternateColorCodes('&', cfg.getString("Motd")) + " \n" + " §7§oEntwickler: §c§oErebosTheDark");
	}

}

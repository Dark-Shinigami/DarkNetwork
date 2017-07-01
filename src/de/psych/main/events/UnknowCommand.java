package de.psych.main.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.help.HelpTopic;

public class UnknowCommand implements Listener {

	public void onEnable() {
		
		
		
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	
	public void onUnknow(PlayerCommandPreprocessEvent e) {
		if(!(e.isCancelled())) {
			Player p = e.getPlayer();
			String msg = e.getMessage().split(" ")[0];
			HelpTopic topic = Bukkit.getServer().getHelpMap().getHelpTopic(msg);
			if(topic == null) {
				
				
				p.sendMessage("§6>> §eDarkNetwork §6| §bAllgemeine Hilfe:");
				p.sendMessage("§b/hub §7Zurück zur Haupt-Lobby");
				p.sendMessage("§b/msg §7Befehle für das MSG-System");
				p.sendMessage("§b/ping §7Zeigt deinen Ping");
				e.setCancelled(true);
			}
			
		}
		
		
		
		
		
	}

}

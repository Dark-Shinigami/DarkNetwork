package de.psych.main.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.help.HelpTopic;

import de.psych.main.Main;

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
				
				p.sendMessage(Main.prI);
				p.sendMessage("");
				p.sendMessage(Main.head+"§7Der Command §c"+msg+" §7ist uns Unbekannt.");
				e.setCancelled(true);
			}
			
		}
		
		
		
		
		
	}

}

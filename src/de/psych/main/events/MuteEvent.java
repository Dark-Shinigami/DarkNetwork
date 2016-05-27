package de.psych.main.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import de.psych.main.Main;
import de.psych.main.commands.Mute_CMD;

public class MuteEvent implements Listener{
	
	@EventHandler
	public void on(AsyncPlayerChatEvent e){
		Player p = e.getPlayer();
		
		if(Mute_CMD.isMuted(p.getName())){
			e.setCancelled(true);
			p.sendMessage(Main.prI);
			p.sendMessage("");
			p.sendMessage(Main.head+"§cDu kannst nicht schreiben.");
		}
		

		
		
	}

}

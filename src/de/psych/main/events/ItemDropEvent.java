package de.psych.main.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class ItemDropEvent implements Listener{
	
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent e){
		
		e.setCancelled(true);
		
	}

}

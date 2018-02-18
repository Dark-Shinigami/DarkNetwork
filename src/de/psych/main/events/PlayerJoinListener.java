package de.psych.main.events;

import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import de.psych.main.commands.Warp;


/**
 * Created by ErebosTheDark 23.05.2016
 */

public class PlayerJoinListener implements Listener{
	@EventHandler
	public void join(PlayerJoinEvent e){
		
		 Warp.tpWarp(e.getPlayer(), "spawn");
		
		if(e.getPlayer().isOp()){
			e.setJoinMessage(null);
			e.getPlayer().sendMessage("§6>> §eDarkNetwork §6| §7Du befindest dich auf §bLobby.");
			e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.AMBIENCE_THUNDER, 1, 1);
			e.getPlayer().setGameMode(GameMode.CREATIVE);
		} else {
			e.setJoinMessage(null);
			e.getPlayer().sendMessage("§6>> §eDarkNetwork §6| §7Du befindest dich auf §bLobby 1.");
			e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.NOTE_PLING, 1, 1);
			
		}
			
		}
		
		
	
	
		
		
		
		
	
	
	@EventHandler
	public void leave(PlayerQuitEvent e){
		e.setQuitMessage(null);
	}
}
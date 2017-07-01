package de.psych.main.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;


public class Reload implements Listener {
	
	int Player = 0;
	String name = "";
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void Baum(PlayerCommandPreprocessEvent e) {
		
		Player p = e.getPlayer();
		String cmd = e.getMessage();
		if(cmd.equalsIgnoreCase("/Reload") || cmd.equalsIgnoreCase("/rl")) {
			if(p.isOp()|| (p.hasPermission("*"))) {
				e.setCancelled(true);
				for(int i = 0; i != 200; i++){
					Bukkit.broadcastMessage("");
				}
				Bukkit.broadcastMessage("§8[§c!§8] §cThe Server is going to Reload!");
				Bukkit.reload();
				Bukkit.broadcastMessage("§8[§a!§8] §aServer sucessfully Reloaded!");
			} 
		}
	}
	@EventHandler
	public void onSign(SignChangeEvent e) {
		if(e.getLine(0).equalsIgnoreCase("[DNetwork]")) {
			e.setLine(0, "§c✘ §3DarkException §c✘");
			e.setLine(1, "§a§oFun,");
			e.setLine(2, "§a§oFriends,");
			e.setLine(3, "§a§oand more...");
		return;	
		}
	}
	
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void Birne(PlayerCommandPreprocessEvent e) {
		
		Player p = e.getPlayer();													//10 Outputs
		String cmd = e.getMessage();
		if(cmd.equalsIgnoreCase("/help") || cmd.equalsIgnoreCase("/?")|| cmd.equalsIgnoreCase("/help 1")||cmd.equalsIgnoreCase("/? 1")) {
			
			e.setCancelled(true);
			p.sendMessage("§6>> §eDarkException §6| §bAllgemeine Hilfe:");
			p.sendMessage("§b/hub §7Zurück zur Haupt-Lobby");
			p.sendMessage("§b/msg §7Befehle für das MSG-System");
			p.sendMessage("§b/ping §7Zeigt deinen Ping");
			
			
			
		}
if(cmd.equalsIgnoreCase("/help2") || cmd.equalsIgnoreCase("/?2") || cmd.equalsIgnoreCase("/help 2")|| cmd.equalsIgnoreCase("/? 2")) {
	
			e.setCancelled(true);
			p.sendMessage("§6>> §eDarkException §6| §bAllgemeine Hilfe:");
			p.sendMessage("§b/hub §7Zurück zur Haupt-Lobby");
			p.sendMessage("§b/msg §7Befehle für das MSG-System");
			p.sendMessage("§b/ping §7Zeigt deinen Ping");		
		}
if(cmd.equalsIgnoreCase("/help3") || cmd.equalsIgnoreCase("/?3") || cmd.equalsIgnoreCase("/help 3")|| cmd.equalsIgnoreCase("/? 3")) {
	
			e.setCancelled(true);
			p.sendMessage("§6>> §eDarkException §6| §bAllgemeine Hilfe:");
			p.sendMessage("§b/hub §7Zurück zur Haupt-Lobby");
			p.sendMessage("§b/msg §7Befehle für das MSG-System");
			p.sendMessage("§b/ping §7Zeigt deinen Ping");
}		
		
		
	}
	
	@EventHandler
	public void onCommandEnter(PlayerCommandPreprocessEvent e){
		Player p = e.getPlayer();
		
		String msg = e.getMessage();
		if(msg.equalsIgnoreCase("/op")){
			if(!(p.hasPermission("dnetwork.cmddeny"))){
				
				e.setCancelled(true);
				p.sendMessage("§6>> §eDarkException §6| §7Keine Berechtigung.");
				
			}
		}
	}
	
	
	@EventHandler
	public void EntityDamage(EntityDamageEvent e){
		
		e.setCancelled(true);
	}
	

	
 }	
	
	
	



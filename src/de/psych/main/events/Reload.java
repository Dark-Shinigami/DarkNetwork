package de.psych.main.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import de.psych.main.Main;

public class Reload implements Listener {
	
	int Player = 0;
	String name = "";
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void Baum(PlayerCommandPreprocessEvent e) {
		
		Player p = e.getPlayer();
		String cmd = e.getMessage();
		if(cmd.equalsIgnoreCase("/Reload") || cmd.equalsIgnoreCase("/rl")) {
			if(p.isOp()) {
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
		if(e.getLine(0).equalsIgnoreCase("[ErebosDEV]")) {
			e.setLine(0, "§c✘ §3ErebosDEV §c✘");
			e.setLine(1, "§7§oJava,");
			e.setLine(2, "§7§n§oPlugins,");
			e.setLine(3, "§7§oand more...");
		return;	
		}
	}
	
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void Birne(PlayerCommandPreprocessEvent e) {
		
		Player p = e.getPlayer();													//10 Outputs
		String cmd = e.getMessage();
		if(cmd.equalsIgnoreCase("/help") || cmd.equalsIgnoreCase("/?")|| cmd.equalsIgnoreCase("/help 1")||cmd.equalsIgnoreCase("/? 1")) {
			
			e.setCancelled(true);
			for(int i = 0; i != 200; i++){
				p.sendMessage("");
			}
			p.sendMessage("§c➤ §aHelp Index - Page §31");
			p.sendMessage("§7[§c§l!§7] Use /? x or /help x to Enter this Index.");
			p.sendMessage("§7[§c§l!§7] §aPlugins:");
			p.sendMessage("§7[§c§l!§7]   §a�?� §3ErebosC0re §7/help ErebosC0re");
			p.sendMessage("§7[§c§l!§7]   §a�?� §3BanManager §7/help BanManager");
			p.sendMessage("§7[§c§l!§7]   §a�?� §3E-Lobby §7/help E-Lobby");
			p.sendMessage("§7[§c§l!§7]   §a�?� §3Kits §7/help Kits");
			p.sendMessage("§7[§c§l!§7]   §a�?� §3WorldEdit §7/help WorldEdit");
			p.sendMessage("§7[§c§l!§7]   §a�?� §7More Plugins / Add-On´s will follow!");
			
			
			
		}
if(cmd.equalsIgnoreCase("/help2") || cmd.equalsIgnoreCase("/?2") || cmd.equalsIgnoreCase("/help 2")|| cmd.equalsIgnoreCase("/? 2")) {
			e.setCancelled(true);
			for(int i = 0; i != 200; i++){
				p.sendMessage("");
			}
			p.sendMessage("§c➤ §aHelp Index - Page §32");
			p.sendMessage("§7[§c§l!§7] Use /? x or /help x to Enter this Index.");
			p.sendMessage("§7[§c§l!§7] §aEssential Commands:");
			p.sendMessage("§7[§c§l!§7]   §a�?� §3/hub §7Return to the Lobby.");
			p.sendMessage("§7[§c§l!§7]   §a�?� §3/credits §7Server/Dev Credits");
			p.sendMessage("§7[§c§l!§7]   §a�?� §3/back §7Fun Command");
			p.sendMessage("§7[§c§l!§7]   §a�?� §3/tempban <1m,1h,1d> §cAdmin Command");
			p.sendMessage("§7[§c§l!§7]   §a�?� §3/unban <player> §cAdmin Command");
			p.sendMessage("§7[§c§l!§7]   §a�?� §3/baninfo <player> §7Is Player <x> Banned?");		
		}
if(cmd.equalsIgnoreCase("/help3") || cmd.equalsIgnoreCase("/?3") || cmd.equalsIgnoreCase("/help 3")|| cmd.equalsIgnoreCase("/? 3")) {
	e.setCancelled(true);
	for(int i = 0; i != 200; i++){
		p.sendMessage("");
	}
	p.sendMessage("§c➤ §aHelp Index - Page §32");
	p.sendMessage("§7[§c§l!§7] Use /? x or /help x to Enter this Index.");
	p.sendMessage("§7[§c§l!§7] §aEssential Commands:");
	p.sendMessage("§7[§c§l!§7]   §a�?� §3/kits §7Kit GUI.");
	p.sendMessage("§7[§c§l!§7]   §a�?� §3/gm1 §cAdmin Command");
	p.sendMessage("§7[§c§l!§7]   §a�?� §3/gm0 §cAdmin Command");
	p.sendMessage("§7[§c§l!§7]   §a�?� §3/zeit <T/N> §cAdmin Command");
	p.sendMessage("§7[§c§l!§7]   §a�?� §3/");
	p.sendMessage("§7[§c§l!§7]   §a�?� §3/");		
}		
		
		
	}
	
	@EventHandler
	public void onCommandEnter(PlayerCommandPreprocessEvent e){
		Player p = e.getPlayer();
		
		String msg = e.getMessage();
		if(msg.equalsIgnoreCase("/op")){
			if(!(p.hasPermission("elobby.cmddeny"))){
				
				e.setCancelled(true);
				p.sendMessage(Main.prefixG+"§7| §a§oInformation");
				p.sendMessage("");
				p.sendMessage(Main.np);
				
			}
		}
	}
	
 }	
	
	
	



package de.psych.main.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Heal_Feed implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		
		if(!(cs instanceof Player)) {
			cs.sendMessage("§cYou are no Player!");
				return true;
				
		}
		Player p = (Player) cs;
		
		
		if (p.hasPermission("dnetwork.feed")) {
		
		if(args.length == 0) {
			
			p.sendMessage("§6>> §eDarkNetwork §6| §7Bitte nutze §b/e <heal/feed> §7oder §b/e <h/f>§7.");
			
		} else if(args.length == 1) {
			
			if(args[0].equalsIgnoreCase("heal") || args[0].equalsIgnoreCase("h")){
				p.sendMessage("§6>> §eDarkNetwork §6| §7Du wurdest geheilt.");
				p.setHealth(20.0);
			} else if(args[0].equalsIgnoreCase("feed") || args[0].equalsIgnoreCase("f")){
				p.sendMessage("§6>> §eDarkNetwork §6| §7Du hungerst nun nicht mehr.");
				p.setFoodLevel(20);
			} else {
				p.sendMessage("§6>> §eDarkNetwork §6| §7Syntax Fehler.");
			}
		} else {
			
			p.sendMessage("§6>> §eDarkNetwork §6| §7Bitte nutze §b/e <heal/feed> §7oder §b/e <h/f>§7.");
		}

		
			

		} else {

			p.sendMessage("§6>> §eDarkNetwork §6| §7Keine Berechtigung.");

		}
		return true;
	

	}
	
}

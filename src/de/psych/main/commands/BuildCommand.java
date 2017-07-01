package de.psych.main.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.psych.main.Main;

public class BuildCommand implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
	sender.sendMessage("§cYou are no Player!");
		return true;
		
		}
		
		Player p = (Player) sender;
		
	if(p.hasPermission("dnetwork.build")){
		if(Main.building.contains(p)){
		Main.building.remove(p);
		Main.moveItems.remove(p);
		p.sendMessage("§6>> §eDarkNetwork §6| §7Du hast den §bBuild Mode §7verlassen.");
		return true;
		}else{
	if(!Main.building.contains(p)){
				Main.building.add(p);
				Main.moveItems.add(p);
				p.sendMessage("§6>> §eDarkNetwork §6| §7Du hast den §bBuild Mode §7betreten.");
				return true;
				}
		}
		return true;
		
	} else {
		p.sendMessage("§6>> §eDarkNetwork §6| §7Keine Berechtigung.");
	}
	return true;
	}
}

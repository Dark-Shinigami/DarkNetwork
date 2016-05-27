package de.psych.main.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.psych.main.Main;

public class Rank_CMD implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if(!(cs instanceof Player)) {
	cs.sendMessage("Du musst ein Spieler sein!");
		return true;
		
		}
		
		Player p = (Player) cs;
		
		if(p.hasPermission("elobby.rank")){
			
			if(args.length != 0){
				p.sendMessage(Main.head+"§cNutze /rank <rank>");
			}
			
			if(p.hasPermission("elobby.rank.dev")){
				
					if(args[0].contains("dev")){
				Main.dev.add(p);
				p.sendMessage(Main.prI);
				p.sendMessage("");
				p.sendMessage(Main.head+"Du wurdest der Gruppe §cdev §7hinzugefügt!");
				
			}  }
			
		if(p.hasPermission("elobby.rank.admin")){
			if (args[0].contains("admin")){
				Main.admin.add(p);
				p.sendMessage(Main.prI);
				p.sendMessage("");
				p.sendMessage(Main.head+"Du wurdest der Gruppe §cadmin §7hinzugefügt!");
			}
			
		}
			
			
			if (args[0].contains("remove")){
				Main.admin.remove(p);
				Main.dev.remove(p);
				p.sendMessage(Main.prI);
				p.sendMessage("");
				p.sendMessage(Main.head+"Du wurdest aus allen Gruppen §centfernt");
			}
			
			
			
			
		}else {
			p.sendMessage(Main.prI);
			p.sendMessage("");
			p.sendMessage(Main.np);
		}
		
		return true;
		
		
	}
	
	
}

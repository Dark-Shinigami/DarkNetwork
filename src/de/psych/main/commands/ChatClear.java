package de.psych.main.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import de.psych.main.Main;

public class ChatClear implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String cmdlabel, String[] args) {
		
		if(sender instanceof ConsoleCommandSender){
			System.out.println("§cHah! You cant clear the Console!");
			return true;
		}
		Player p = (Player) sender;
		
		if(!(p.hasPermission("elobby.cc"))){
			p.sendMessage(Main.prefixG+"§cAcces Denied!");
			return true;
		}
	if(args.length == 0){	
		for(int i=0; i != 200; i++){
			Bukkit.broadcastMessage("");
		}
		Bukkit.broadcastMessage(Main.prefixG+"   §7|   §a§oInformation");
		Bukkit.broadcastMessage("");
		Bukkit.broadcastMessage("");
		Bukkit.broadcastMessage("");
		Bukkit.broadcastMessage("");
		Bukkit.broadcastMessage("");
		Bukkit.broadcastMessage("");
		Bukkit.broadcastMessage("");
		Bukkit.broadcastMessage("");
		Bukkit.broadcastMessage("§7§l[§c➤§7§l] §7Der Chat wurde von §c§o"+p.getName()+" §7geleert.");
		return true;
	}else if(args.length == 1){
		if(!(p.hasPermission("elobby.cco"))){
			p.sendMessage(Main.prefixG);
			p.sendMessage("");
			p.sendMessage(Main.np);
			return true;
		}
		try{
			Player a = Bukkit.getPlayer(args[0]);
			
			for(int i = 0; i != 200; i++){
				a.sendMessage("");
			}
			a.sendMessage(Main.prI);
			a.sendMessage("");
			a.sendMessage(Main.head+"§7Dein Chat wurde von §c"+p.getName()+" §7geleert.");
			p.sendMessage(Main.prI);
			p.sendMessage("");
			p.sendMessage(Main.head+"§7Du hast den Chat von §c"+a.getName()+" §7geleert.");
			return true;
		}catch (NullPointerException e){
			p.sendMessage(Main.prI);
			p.sendMessage("§7§l[§c➤§7§l] §7Der angegebene Spieler ist §coffline§7!");
			p.sendMessage("§7§l[§c➤§7§l] §7Eingabe: §a"+args[0]);
			return true;
		}
	} else {
		
		p.sendMessage(Main.prefixG+"Benutzte /cc [Spieler]");
	}
		
		
		return false;
	}

}

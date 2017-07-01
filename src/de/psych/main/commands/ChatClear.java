package de.psych.main.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class ChatClear implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String cmdlabel, String[] args) {
		
		if(sender instanceof ConsoleCommandSender){
			System.out.println("§cHah! You cant clear the Console!");
			return true;
		}
		Player p = (Player) sender;
		
		if(!(p.hasPermission("dnetwork.cc"))){
			p.sendMessage("§6>> §eDarkNetwork §6| §7Keine Berechtigung.");
			return true;
		}
	if(args.length == 0){	
		for(int i=0; i != 200; i++){
			Bukkit.broadcastMessage("");
		}
		Bukkit.broadcastMessage("");
		Bukkit.broadcastMessage("");
		Bukkit.broadcastMessage("");
		Bukkit.broadcastMessage("");
		Bukkit.broadcastMessage("");
		Bukkit.broadcastMessage("");
		Bukkit.broadcastMessage("");
		Bukkit.broadcastMessage("");
		Bukkit.broadcastMessage("");
		Bukkit.broadcastMessage("§6>> §eDarkNetwork §6| §7Der Chat wurde geleert.");
		return true;
	}else if(args.length == 1){
		if(!(p.hasPermission("dnetwork.cco"))){
			p.sendMessage("§6>> §eDarkNetwork §6| §7Keine Berechtigung.");
			return true;
		}
		try{
			Player a = Bukkit.getPlayer(args[0]);
			
			for(int i = 0; i != 200; i++){
				a.sendMessage("");
			}
			a.sendMessage("§6>> §eDarkNetwork §6| §7Dein Chat wurde geleert.");
			p.sendMessage("§6>> §eDarkNetwork §6| §7Du hast den Chat von §b"+a.getName()+" §7geleert.");
			return true;
		}catch (NullPointerException e){
			p.sendMessage("§6>> §eDarkNetwork §6| §7Der angegebene Spieler '§b"+args[0]+"§7' ist offline.");
			return true;
		}
	} else {
		
		p.sendMessage("§6>> §eDarkNetwork §6| §7Bitte nutze §b/cc <Spieler Name>§7.");
	}
		
		
		return false;
	}

}

package de.psych.main.commands;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class HUB_CMD implements CommandExecutor{
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String cmdlabel, String[] args) {
		
		if(sender instanceof ConsoleCommandSender){
			System.out.println("§cYou are no player!");
			return true;
		}
		Player p = (Player) sender;
		
		
		World w = p.getLocation().getWorld();
		Location spawn = new Location(w,-395.834,63.00000,124.481);
		p.teleport(spawn);
		
		p.sendMessage("§7Du wurdest zur §bLobby §7teleportiert.");
		
			return true;
		
		
		
		
	}

}

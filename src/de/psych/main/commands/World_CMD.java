package de.psych.main.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class World_CMD implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		
		
		if(cs instanceof Player){
			Player p = (Player) cs;
			
			
			//p.teleport(Main.otherWorld.getSpawnLocation());
			
			
			String name = p.getName();
			
			p.sendMessage("§c"+Bukkit.getPlayer(name).getWorld().getName());
			
			return true;
			
		}
		
		return false;
	}
	
	

}

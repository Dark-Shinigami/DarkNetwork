package de.psych.main.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_8_R3.EntityPlayer;

public class Ping_CMD implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		
		if(cs instanceof ConsoleCommandSender){
			Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"LoL Dude? Are you fucking retardet ?!? The Console Ping is "+ChatColor.YELLOW+"0ms "+ChatColor.RED+"!");
			return true;
		}
		
		Player p = (Player)cs;
		
		
		
				if(args.length == 0){
					p.sendMessage("§6>> §eDarkNetwork §6| §7Dein Ping: §e"+ getPing(p) +" ms");
				}
			
		
		
		return false;
	}
	
	public int getPing(Player p){
		CraftPlayer pingc = (CraftPlayer) p;
		EntityPlayer pinge = pingc.getHandle();
		return pinge.ping;
	}

}

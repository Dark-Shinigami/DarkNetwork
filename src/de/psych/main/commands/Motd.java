package de.psych.main.commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class Motd implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String cmdlabel, String[] args) {
		if(!(cs instanceof Player)) {
			cs.sendMessage("§cYou are no Player!");
				return true;
				
		}
		Player p = (Player) cs;
		
		if(p.hasPermission("motd")){
			
			if(args.length >= 1){
			String motd = "";
			for(int i = 0; i < args.length; i++){
				motd = motd + " " + args[i];
			}
			
			File file = new File("plugins//DarkNetwork//Motd.yml");
			File ordner = new File("plugins//DarkNetwork");
			
			if(!(ordner.exists())){
				ordner.mkdir();
			}
			
			if(!(file.exists())){
				try {
					file.createNewFile();
				} catch (IOException e) {
				
					e.printStackTrace();
				}
			}
			
			YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
			
			cfg.set("Motd", motd);
			try {
				cfg.save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			p.sendMessage("§6>> §eDarkNetwork §6| §7Die neue MotD lautet: "+ChatColor.translateAlternateColorCodes('&', motd));
			
			return true;
		}else if(args.length == 0){
			p.sendMessage("§6>> §eDarkNetwork §6| §7Bitte nutze §b/motd §7[§bNeue Motd§7].");
			return true;
		}
			
		} else {
			p.sendMessage("§6>> §eDarkNetwork §6| §7Keine Berechtigung.");
			
			return true;
		}
		
		
		
		return false;
		
		
		
	}

}

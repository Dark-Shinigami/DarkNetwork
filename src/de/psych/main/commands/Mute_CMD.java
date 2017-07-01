package de.psych.main.commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;


public class Mute_CMD implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String cmdlabel, String[] args) {
		
		if(cs instanceof ConsoleCommandSender){
			cs.sendMessage("You are no Player!");
			return true;
		}
		Player p = (Player) cs;
		
		if(!(p.hasPermission("dnetwork.mute"))){
			
			p.sendMessage("§6>> §eDarkNetwork §6| §7Keine Berechtigung.");
			return true;
		}
		
		if(args.length == 1){
			if(isMuted(args[0])){
			mute(args[0],false);
			p.sendMessage("§6>> §eDarkNetwork §6| §7Der Spieler §b"+args[0]+" §7wurde entmuted.");
		} else {
			mute(args[0],true);
			p.sendMessage("§6>> §eDarkNetwork §6| §7Der Spieler §b"+args[0]+" §7wurde gemuted.");
		}
		}else {
			p.sendMessage("§6>> §eDarkNetwork §6| §7Bitte nutze §b/mute <Spieler>§7.");
		
		}
		return true;
		
		
	}
	
	public static boolean isMuted(String playername){
		File ordner = new File ("plugins//DarkNetwork");
		File file = new File("plugins//DarkNetwork//mute.yml");
		
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
		boolean b = cfg.getBoolean(playername.toLowerCase());
		return b;
	}
	
	
	public static void mute(String playername, boolean b){
		File ordner = new File ("plugins//DarkNetwork");
		File file = new File("plugins//DarkNetwork//mute.yml");
		
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
		cfg.set(playername.toLowerCase(), b);
		try {
			cfg.save(file);
		} catch (IOException e) {
		}
	}
	
}

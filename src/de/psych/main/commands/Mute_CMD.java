package de.psych.main.commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import de.psych.main.Main;

public class Mute_CMD implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String cmdlabel, String[] args) {
		
		if(cs instanceof ConsoleCommandSender){
			cs.sendMessage("Du musst ein Spieler sein.");
			return true;
		}
		Player p = (Player) cs;
		
		if(!(p.hasPermission("elobby.mute"))){
			
			p.sendMessage(Main.prI);
			p.sendMessage("");
			p.sendMessage(Main.np);
			return true;
		}
		
		if(args.length == 1){
			if(isMuted(args[0])){
			mute(args[0],false);
			p.sendMessage(Main.prI);
			p.sendMessage("");
			p.sendMessage(Main.head+"§cDer Spieler §3"+args[0]+" §cwurde entmuted!");
		} else {
			mute(args[0],true);
			p.sendMessage(Main.prI);
			p.sendMessage("");
			p.sendMessage(Main.head+"§aDer Spieler §3"+args[0]+" §awurde gemuted!");
		}
		}else {
			p.sendMessage(Main.prI);
			p.sendMessage("");
			p.sendMessage(Main.head+"§c/mute <Spieler>");
		
		}
		return true;
		
		
	}
	
	public static boolean isMuted(String playername){
		File ordner = new File ("plugins//E-Lobby");
		File file = new File("plugins//E-Lobby//mute.yml");
		
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
		File ordner = new File ("plugins//E-Lobby");
		File file = new File("plugins//E-Lobby//mute.yml");
		
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

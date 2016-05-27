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

public class Register_CMD implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String cmdlabel, String[] args) {
		
		if(cs instanceof ConsoleCommandSender){
			cs.sendMessage("Du musst ein Spieler sein.");
			return true;
		}
		Player p = (Player) cs;
		
		if(args.length == 2){
			
			if(isRegisterd(p)){
				p.sendMessage(Main.prI);
				p.sendMessage("");
				p.sendMessage(Main.head+"§cDu bist bereits registriert!");
				return true;
			}
			String password1 = args[0];
			String password2 = args[1];
			if(password1.equals(password2)){
				register(p, password1);
				
				p.sendMessage(Main.prI);
				p.sendMessage("");
				p.sendMessage(Main.head+"§aDu hast dich erfolgreich registriert!");
				p.sendMessage(Main.head+"§7Logge dich jetzt mit §3/login <Passwort> §7ein!");
			}else{
				p.sendMessage(Main.prI);
				p.sendMessage("");
				p.sendMessage(Main.head+"§cDu musst 2x das gleiche Passwort angeben!");
			}
			
			
		} else {
			p.sendMessage(Main.prI);
			p.sendMessage("");
			p.sendMessage(Main.head+"§cBitte benutze /register <Passwort> <Passwort>");
		}

		return true;
	}
	
	public static boolean isRegisterd(Player p){
		boolean isRegisterd = false;
		
		File ordner = new File("plugins//E-Lobby//Login");
		File file = new File("plugins//E-Lobby//Login//Daten.yml");
		
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
		if(cfg.contains("Login.Daten."+p.getName())){
			isRegisterd = true;
		}
		
		
		return isRegisterd;
	}
	
	public static void register(Player p, String password){
		File ordner = new File("plugins//E-Lobby//Login");
		File file = new File("plugins//E-Lobby//Login//Daten.yml");
		
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
		
		cfg.set("Login.Daten."+p.getName()+".Password", password);
		
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

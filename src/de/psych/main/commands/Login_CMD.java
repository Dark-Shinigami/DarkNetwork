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

public class Login_CMD implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String cmdlabel, String[] args) {
		
		if(cs instanceof ConsoleCommandSender){
			cs.sendMessage("Du musst ein Spieler sein.");
			return true;
		}
		Player p = (Player) cs;
		// login <Passwort>
		
		if(args.length == 1){
			if(isRegisterd(p) == false){
				p.sendMessage(Main.prI);
				p.sendMessage("");
				p.sendMessage(Main.head+"§cDu musst dich erst registrieren.");
				p.sendMessage(Main.head+"§cVerwende : /register <Passwort> <Passwort>");
			} else {
				String password = args[0];
				
				if(password.equals(getPassword(p))){
					if(Main.login.contains(p.getName())){
						p.sendMessage(Main.prI);
						p.sendMessage("");
						p.sendMessage(Main.head+"§cDu bist bereits eingeloggt!");
						return true;
					}
					Main.login.add(p.getName());
					p.sendMessage(Main.prI);
					p.sendMessage("");
					p.sendMessage(Main.head+"§aDu hast dich erfolgreich eingeloggt!");
					
				} else {
					p.sendMessage(Main.prI);
					p.sendMessage("");
					p.sendMessage(Main.head+"§cFalsches Passwort!");
				}
			}
			
		} else {
			p.sendMessage(Main.prI);
			p.sendMessage("");
			p.sendMessage(Main.head+"§cBitte benutze /login <Passwort>");
		}
		
		
		return true;
	}
	
	public static void login(Player p, boolean login){

		File ordner = new File("plugins//E-Lobby//Login");
		File file = new File("plugins//E-Lobby//Login//Login");
		
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
		
		cfg.set("Login."+p.getName(), login);
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static  String getPassword(Player p){
		File ordner = new File("plugins//E-Lobby//Login");
		File file = new File("plugins//E-Lobby//Login//Daten.yml");
		String password = null;
		
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
		
		if(isRegisterd(p)== true){
			password = cfg.getString("Login.Daten."+p.getName()+".Password");
			
		}
		return password;
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
	
	public static boolean isLoggedIn(Player p){
		boolean isLoggedIn = false;

		File ordner = new File("plugins//E-Lobby//Login");
		File file = new File("plugins//E-Lobby//Login//Login");
		
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
		if(cfg.contains("Login."+p.getName())){
			isLoggedIn = true;
		}
		
		
		return isLoggedIn;
	}

}

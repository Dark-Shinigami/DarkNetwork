package de.psych.main.commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import de.psych.main.Main;

public class Msg implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String cmdlabel, String[] args) {
		
		if(cs instanceof ConsoleCommandSender){
			
			if(args.length >= 2){
				Player a = Bukkit.getPlayer(args[0]);
				try{
				
				if(a == cs){
				
					cs.sendMessage("Es müssen verschiedene Spieler angegeben sein!");
					
				}
				
				String msg = "";
				for(int i = 1; i != args.length; i++){
					msg = msg + " " + args[i];
				}
				
				cs.sendMessage("[MSG] Konsole ->  "+a.getName()+" >> "+msg);
				a.sendMessage("§7[§6MSG§7] §cConsole§6 ➤§9 You §6>> §b"+msg);
				
				
				} catch (NullPointerException e){
				
					cs.sendMessage("Der Spieler ist offline!");
					
				}
				
			} else {
		
				cs.sendMessage("Bitte benutze /msg <Spieler> <Nachicht>");
				
			}
			return true;
			
		}
		
		
		Player p = (Player) cs;
		
		if(args.length >= 2){
			Player a = p.getServer().getPlayer(args[0]);
			try{
			
			if(a == p){
				p.sendMessage(Main.prefixG);
				p.sendMessage("");
				p.sendMessage(Main.head+"Er müssen verschiedene Spieler angegeben sein!");
				return true;
			}
			
			String msg = "";
			for(int i = 1; i != args.length; i++){
				msg = msg + " " + args[i];
			}
			
			p.sendMessage("§7[§6MSG§7] §9You §6 ➤§9 "+a.getName()+" §6>> §b"+msg);
			a.sendMessage("§7[§6MSG§7] §9"+p.getName()+"§6 ➤§9 You §6>> §b"+msg);
			
			for(Player all : Bukkit.getOnlinePlayers()){
				File ordner = new File("plugins//Pagian");
				File file = new File("plugins//Pagian//MSGSpyOption");
				
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
				
				boolean spy = cfg.getBoolean(all.getName());
				
				if(spy == true){
					all.sendMessage("§7[§6MSG §cSPY§7] §7From = §9"+p.getName()+"§8 ➤§9"+a.getName()+" §8>> §b"+msg);
				}
			}
			
			} catch (NullPointerException e){
				p.sendMessage(Main.prefixG);
				p.sendMessage(Main.head+"Der Spieler ist offline!");
				return true;
			}
			
		} else {
			p.sendMessage(Main.prI);
			p.sendMessage("");
			p.sendMessage(Main.head+"Bitte benutze /msg <Spieler> <Nachicht>");
			return true;
		}
		
		return false;
	}
	
	

}

package de.psych.main;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.psych.main.commands.*;
import de.psych.main.events.*;

public class Main extends JavaPlugin {
	
	/*     Plugin developet by
	 * 
	 * 			ErebosTheDark // Simon Heinen
	 * 
	 * 		All rights reserverd.
	 * 
	 * 			20.02.2016 ~ Germany
	 * 
	 */
	
	
	Logger log = Bukkit.getLogger();
	public static String prefixC = "[E-Lobby] ";
	public static String co = "§a§o";
	public static String prefixG = "§7§l[§c➤§7§l] §3§lE-Lobby§r "+co;
	public static String head = "§7§l[§c➤§7§l] §7";
	public static String np = head+"§cKeine Berechtigung!";
	public static String prI = Main.prefixG+"§7| §a§oInformation";
	public static String prP = Main.prefixG+"§7| §a§oPunkte System";
	public static List<Player> building = new ArrayList<Player>();
	public static List<Player> moveItems = new ArrayList<Player>();
	public static ArrayList<String> login = new ArrayList<String>();
	public static List<Player> dev = new ArrayList<Player>();
	public static List<Player> admin = new ArrayList<Player>();
	public void onEnable(){
		registerEvents();
		registerCommands();
		log.info(prefixC + ("Plugin aktiviert. Aktuelle Version: ")+this.getDescription().getVersion()+(". Entwickler: "+this.getDescription().getAuthors()));
		this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		
		
	}
	
	public void onDisable(){
		log.info(prefixC+ ("Plugin deaktiviert."));
	}
	
	public void registerCommands() {
		this.getCommand("cc").setExecutor(new ChatClear());
		this.getCommand("motd").setExecutor(new Motd());
		this.getCommand("msg").setExecutor(new Msg());
		this.getCommand("msgspy").setExecutor(new Msgspy());
		this.getCommand("punkte").setExecutor(new Punkte_CMD());
		this.getCommand("build").setExecutor(new BuildCommand());
		this.getCommand("warp").setExecutor(new Warp());
		this.getCommand("login").setExecutor(new Login_CMD());
		this.getCommand("register").setExecutor(new Register_CMD());
		this.getCommand("mute").setExecutor(new Mute_CMD());
		this.getCommand("rank").setExecutor(new Rank_CMD());
		this.getCommand("hub").setExecutor(new HUB_CMD());
	}
	
	public void registerEvents() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new Reload(), this);
		pm.registerEvents(new ServerPing(), this);
		pm.registerEvents(new UnknowCommand(), this);
		pm.registerEvents(new JoinListener(), this);
		pm.registerEvents(new RightClickListener(), this);
		pm.registerEvents(new ItemMoveListener(this), this);
		pm.registerEvents(new LoginListener(), this);
		pm.registerEvents(new MuteEvent(), this);
		
	}
	
	
	
}

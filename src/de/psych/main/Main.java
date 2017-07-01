package de.psych.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;

import de.psych.apis.TablistAPI;
import de.psych.main.commands.BanCommands;
import de.psych.main.commands.BuildCommand;
import de.psych.main.commands.ChatClear;
import de.psych.main.commands.GM0;
import de.psych.main.commands.GM1;
import de.psych.main.commands.Heal_Feed;
import de.psych.main.commands.Motd;
import de.psych.main.commands.Msg;
import de.psych.main.commands.Msgspy;
import de.psych.main.commands.Mute_CMD;
import de.psych.main.commands.PermCmd;
import de.psych.main.commands.Ping_CMD;
import de.psych.main.commands.Punkte_CMD;
import de.psych.main.commands.StaffChatCMD;
import de.psych.main.commands.TagNacht;
import de.psych.main.commands.Warp;
import de.psych.main.commands.WorldReset_CMD;
import de.psych.main.commands.World_CMD;
import de.psych.main.events.ItemDropEvent;
import de.psych.main.events.ItemMoveListener;
import de.psych.main.events.JoinListener;
import de.psych.main.events.MuteEvent;
import de.psych.main.events.PlayerChatListener;
import de.psych.main.events.PlayerJoinListener;
import de.psych.main.events.PlayerLogin;
import de.psych.main.events.PlayerMoveListener;
import de.psych.main.events.PlayerToggleFly;
import de.psych.main.events.Reload;
import de.psych.main.events.ScoreboardEvent;
import de.psych.main.events.ServerPing;
import de.psych.main.events.UnknowCommand;
import de.psych.main.utils.BanManager;
import de.psych.main.utils.FileManager;
import de.psych.mysql.MySQL;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;

public class Main extends JavaPlugin implements Listener {
	
	/*     Plugin developet by
	 * 
	 * 			ErebosTheDark // Simon Heinen
	 * 
	 * 		All rights reserverd.
	 * 
	 * 			20.02.2016 ~ Germany
	 * 
	 */
    public static File file = new File("plugins/DarkNetwork", "actionbar.yml");
    public static FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
    int i = 0;
    int r = 0;
	public static World otherWorld;
	File backupWorldFile;
	Scoreboard scb;
	Logger log = Bukkit.getLogger();
	public static String prefixC = "[DarkException] ";
	public static String co = "§a§o";
	public static String prefixG = "§7§l[§c➤§7§l] §3§lDarkException§r "+co;
	public static String head = "§7§l[§c➤§7§l] §7";
	public static String np = "§cKeine Berechtigung!";
	public static String prI = Main.prefixG+"§7| §a§oInformation";
	public static String prP = Main.prefixG+"§7| §a§oPunkte System";
	public static List<Player> building = new ArrayList<Player>();
	public static List<Player> moveItems = new ArrayList<Player>();
	public static ArrayList<String> login = new ArrayList<String>();
	public static List<Player> dev = new ArrayList<Player>();
	public static List<Player> admin = new ArrayList<Player>();
    public static ArrayList<Player> staff = new ArrayList<>();
    public static Main plugin;
    public static Main instance;

    	public static Main getInstance() {
		return instance;
	}
    	
	public void onEnable(){
		this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		registerEvents();
		registerCommands();
		
	      if(!file.exists()) {
	            try {
	                file.createNewFile();
	                cfg = YamlConfiguration.loadConfiguration(file);
	                cfg.save(file);
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
		
	      List<String> originalList = cfg.getStringList("broadcast");
	        List<String> newList = new ArrayList<>();
	       
	        for(String s : originalList) {
	            newList.add(s.replace("&", "§"));
	        }
	       
		
		

		FileManager.setStandartMySQL();
		FileManager.readMySQL();
		MySQL.connect();
		MySQL.createTable();
		
		@SuppressWarnings("deprecation")
		OfflinePlayer op = Bukkit.getOfflinePlayer("DarkException");
		
		BanManager.unban(op.getUniqueId().toString());
		
		otherWorld = Bukkit.createWorld(new WorldCreator("limbo"));
		
		
		Bukkit.getConsoleSender().sendMessage("§6********************");
		Bukkit.getConsoleSender().sendMessage("     §cDarkException    ");
		Bukkit.getConsoleSender().sendMessage("§7--------------------");
		Bukkit.getConsoleSender().sendMessage("§aVersion: §e"+this.getDescription().getVersion());
		Bukkit.getConsoleSender().sendMessage("§7--------------------");
		Bukkit.getConsoleSender().sendMessage("§aDev: §eDarkException");
		Bukkit.getConsoleSender().sendMessage("§7--------------------");
		Bukkit.getConsoleSender().sendMessage("     §eDarkException    ");
		Bukkit.getConsoleSender().sendMessage("§6********************");
		
		
		this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		
		
		 Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {@Override public void run() {            	
        	 for(	Player p : Bukkit.getOnlinePlayers()){
        		 TablistAPI.sendTabTitle(p, "\n§c§nDarkException\n", 
    				"\n§bBAUSERVER");}}}, 20,5);
		 
		scb = Bukkit.getScoreboardManager().getNewScoreboard();
		scb.registerNewTeam("00000Admin");
		scb.registerNewTeam("00001Mod");
		scb.registerNewTeam("00002Dev");
		scb.registerNewTeam("00003Build");
		scb.registerNewTeam("00004Spieler");
		
		scb.getTeam("00000Admin").setPrefix("§4Admin §7| §4");
		scb.getTeam("00001Mod").setPrefix("§cMod §7| §c");
		scb.getTeam("00002Dev").setPrefix("§bDev §7| §b");
		scb.getTeam("00003Build").setPrefix("§2Builder §7| §2");
		scb.getTeam("00004Spieler").setPrefix("§aSpieler §7| ");
		
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
            for(	Player p : Bukkit.getOnlinePlayers()){
            	ScoreboardEvent.setBoard(p);
            	setPrefix(p);
            }
            }
    }, 20, 20*2);
	}
	public static Main main;public Main() {main = this;}public static Main getMain() {return main;}
	

	
	public void onDisable(){
		MySQL.close();
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
	//	this.getCommand("login").setExecutor(new Login_CMD());
	//	this.getCommand("register").setExecutor(new Register_CMD());
		this.getCommand("mute").setExecutor(new Mute_CMD());
		this.getCommand("ping").setExecutor(new Ping_CMD());
        this.getCommand("staff").setExecutor(new StaffChatCMD());
        this.getCommand("staffchat").setExecutor(new StaffChatCMD());
        this.getCommand("sc").setExecutor(new StaffChatCMD());
        this.getCommand("permission").setExecutor(new PermCmd(this));
		this.getCommand("gm1").setExecutor(new GM1());
		this.getCommand("gm0").setExecutor(new GM0());
		this.getCommand("time").setExecutor(new TagNacht());
		this.getCommand("e").setExecutor(new Heal_Feed());
		this.getCommand("world").setExecutor(new World_CMD());
		this.getCommand("reset").setExecutor(new WorldReset_CMD(this));
		
		BanCommands banCMD = new BanCommands();
		getCommand("ban").setExecutor(banCMD);
		getCommand("tempban").setExecutor(banCMD);
		getCommand("unban").setExecutor(banCMD);
		getCommand("check").setExecutor(banCMD);
	}

	public void registerEvents() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(this,this);
		pm.registerEvents(new Reload(), this);
		pm.registerEvents(new ServerPing(), this);
		pm.registerEvents(new UnknowCommand(), this);
		pm.registerEvents(new JoinListener(), this);
		pm.registerEvents(new ItemMoveListener(this), this);
	//	pm.registerEvents(new LoginListener(), this);
		pm.registerEvents(new MuteEvent(), this);
        pm.registerEvents(new PlayerChatListener(), this);
        pm.registerEvents(new PlayerJoinListener(), this);
        pm.registerEvents(new ItemDropEvent(), this);
        pm.registerEvents(new PlayerMoveListener(), this);
        pm.registerEvents(new PlayerToggleFly(), this);
        pm.registerEvents(new PlayerLogin(), this);
        
	}
	
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		
		setPrefix(e.getPlayer());
	}
	
	   public void setActionBar(Player player, String message) {
	        CraftPlayer p = (CraftPlayer) player;
	        IChatBaseComponent ibc = ChatSerializer.a("{\"text\": \"" + message + "\"}");
	        PacketPlayOutChat packet = new PacketPlayOutChat(ibc, (byte) 2);
	        p.getHandle().playerConnection.sendPacket(packet);
	    }
	
	@SuppressWarnings("deprecation")
	public void setPrefix(Player p){
		String team1 = " ";
		if(p.hasPermission("dnetwork.admin")){
			team1 = "00000Admin";
		}else if(p.hasPermission("dnetwork.dev")){
			team1 = "00002Dev";
		}else if(p.hasPermission("dnetwork.mod")){
			team1 = "00001Mod";
		}else if(p.hasPermission("dnetwork.build")){
			team1 = "00003Build";
		}else{
			team1 = "00004Spieler";
		}
		scb.getTeam(team1).addPlayer(p);
		
		p.setDisplayName(scb.getTeam(team1).getPrefix()+p.getName());
		
		for(Player all:Bukkit.getOnlinePlayers()){
			all.setScoreboard(scb);
		}
	}
}

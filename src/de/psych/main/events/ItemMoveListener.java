package de.psych.main.events;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import de.psych.main.Main;
import de.psych.main.utils.Bungee;


public class ItemMoveListener implements Listener {
	
	Main pl;
	
	public ItemMoveListener(Main main){
		this.pl = main;
	}
	
	@EventHandler
	public void onItemMove(InventoryClickEvent e) {
		
		Player p = (Player)e.getWhoClicked();
			
		
		final Inventory stats = Bukkit.createInventory(p, 9*3, "§e§l>> §cStatistiken");
		final Inventory friends = Bukkit.createInventory(p, 9*6, "§e§l>> §cFreunde-Verwaltung");
		final Inventory einst = Bukkit.createInventory(p, 9*4, "§e§l>> §cEinstellungen");
		final Inventory meinprofil = Bukkit.createInventory(p, 9*3, "§e§l>> §cMein Profil");
		
		ItemStack space = new ItemStack(Material.STAINED_GLASS_PANE,1,(short)7);
		ItemMeta spaceM = space.getItemMeta();
		spaceM.setDisplayName(" ");
		space.setItemMeta(spaceM);
		
		ItemStack back = new ItemStack(Material.BARRIER);
		ItemMeta backM = back.getItemMeta();
		backM.setDisplayName("§c§lZurück");
		back.setItemMeta(backM);
		
		ItemStack Bed = new ItemStack(Material.BED);
		ItemMeta BedM = Bed.getItemMeta();
		BedM.setDisplayName("§6§l>> §r§eBedwars Duel");
		Bed.setItemMeta(BedM);
		
		ItemStack Unknow = new ItemStack(Material.STAINED_GLASS_PANE,1,(short)6);
		ItemMeta UnknowM = Bed.getItemMeta();
		UnknowM.setDisplayName("§6§l>> §cSoon §e§l§k!");
		Unknow.setItemMeta(UnknowM);
		
		ItemStack kopfStats = new ItemStack(Material.SKULL_ITEM,1,(short)3);
		SkullMeta kopfStatsM = (SkullMeta) kopfStats.getItemMeta();
		kopfStatsM.setOwner(e.getWhoClicked().getName());
		kopfStatsM.setDisplayName("§6§l>> §eStatistiken");
		kopfStatsM.setLore(Arrays.asList(" ","§e§l>> §7"+e.getWhoClicked().getName()));
		kopfStats.setItemMeta(kopfStatsM);
		
		ItemStack statsitem = new ItemStack(Material.BOOK);
		ItemMeta statsitemM = statsitem.getItemMeta();
		statsitemM.setDisplayName("§6§l>> §eStatistiken");
		statsitemM.setLore(Arrays.asList("§b§nLinksklick: §r§7Deine Statistiken anzeigen","§b§nRechtsklick: §r§7Statistiken von einem","§7anderen Spieler anzeigen"));
		statsitem.setItemMeta(statsitemM);
		
		ItemStack party = new ItemStack(Material.CAKE);
		ItemMeta partyM = party.getItemMeta();
		partyM.setDisplayName("§6§l>> §eParty-Verwaltung");
		partyM.setLore(Arrays.asList("§b§nLinks- /Rechtsklick: §r§7Party-Verwaltung","§7anzeigen"));
		party.setItemMeta(partyM);
		
		ItemStack friendsitem = new ItemStack(Material.SKULL_ITEM,1,(short)3);
		SkullMeta friendsitemM = (SkullMeta) friendsitem.getItemMeta();
		friendsitemM.setOwner("ErebosTheDark");
		friendsitemM.setDisplayName("§6§l>> §eFreunde-Verwaltung");
		friendsitemM.setLore(Arrays.asList("§b§nLinks- /Rechtsklick: §r§7Freunde-Verwaltung","§7anzeigen"));
		friendsitem.setItemMeta(friendsitemM);

		ItemStack settings = new ItemStack(Material.REDSTONE_COMPARATOR);
		ItemMeta settingsM = settings.getItemMeta();
		settingsM.setDisplayName("§6§l>> §eEinstellungen");
		settingsM.setLore(Arrays.asList("§b§nLinks- /Rechtsklick: §r§7Einstellungen","§7anzeigen"));
		settings.setItemMeta(settingsM);
		
		ItemStack profil = new ItemStack(Material.SKULL_ITEM,1,(short)3);
		SkullMeta profilM = (SkullMeta) profil.getItemMeta();
		profilM.setOwner(p.getName());
		profilM.setDisplayName("§6§l>> §c§l"+p.getName());
		
		ItemStack freundeV = new ItemStack(Material.SKULL_ITEM,1,(short)3);
		SkullMeta freundeVM = (SkullMeta) freundeV.getItemMeta();
		freundeVM.setOwner(e.getWhoClicked().getName());
		freundeVM.setDisplayName("§6§l>> §eFreunde-Verwaltung");
		freundeVM.setLore(Arrays.asList("§7Verwalte deine Freunde"));
		freundeV.setItemMeta(freundeVM);
		
		ItemStack setup = new ItemStack(Material.REDSTONE_COMPARATOR);
		ItemMeta setupM = setup.getItemMeta();
		setupM.setDisplayName("§6§l>> §r§eEinstellungen");
		setupM.setLore(Arrays.asList("§7Deine Einstellungen"));
		setup.setItemMeta(setupM);
		
		// Statistiken
		stats.setItem(0, space);stats.setItem(1, space);stats.setItem(2, space);stats.setItem(3, space);stats.setItem(4, kopfStats);stats.setItem(5, space);
		stats.setItem(6, space);stats.setItem(7, space);stats.setItem(8, space);stats.setItem(9, space);stats.setItem(13, space);stats.setItem(17, space);
		stats.setItem(19, space);stats.setItem(20, space);stats.setItem(21, space);stats.setItem(22, space);stats.setItem(23, space);stats.setItem(24, space);
		stats.setItem(25, space);stats.setItem(26, space);stats.setItem(18, back);stats.setItem(10, Bed);stats.setItem(11, Unknow);stats.setItem(12, Unknow);
		stats.setItem(14, Unknow);stats.setItem(15, Unknow);stats.setItem(16, Unknow);
		// Statistiken
		
		
		// Mein Profil
		
		if(p.hasPermission("dnetwork.admin")){
			
			profilM.setLore(Arrays.asList("§7----------------","§7Rang: §4Admin"));
			
		}else if(p.hasPermission("dnetwork.dev")){
			profilM.setLore(Arrays.asList("§7----------------","§7Rang: §9Developer"));
		}else if(p.hasPermission("dnetwork.mod")){
			profilM.setLore(Arrays.asList("§7----------------","§7Rang: §2Moderator"));
		}else {
			profilM.setLore(Arrays.asList("§7----------------","§7Rang: §aSpieler"));
		}
		
		profil.setItemMeta(profilM);
		
		meinprofil.setItem(0, space); meinprofil.setItem(1, space); meinprofil.setItem(2, space); meinprofil.setItem(3, space);
		meinprofil.setItem(4, profil);
		meinprofil.setItem(12, friendsitem);
		meinprofil.setItem(14, party);
		meinprofil.setItem(10, statsitem);
		meinprofil.setItem(15, space);
		meinprofil.setItem(5, space);meinprofil.setItem(7, space); meinprofil.setItem(8, space);meinprofil.setItem(9, space);meinprofil.setItem(6, space);meinprofil.setItem(11, space);
		meinprofil.setItem(13, space); meinprofil.setItem(17, space); meinprofil.setItem(18, space); meinprofil.setItem(19, space);meinprofil.setItem(20, space);meinprofil.setItem(22, space);
		meinprofil.setItem(21, space); meinprofil.setItem(23, space); meinprofil.setItem(24, space); meinprofil.setItem(25, space); meinprofil.setItem(26, space);
		meinprofil.setItem(16, settings);
		
		// Mein Profil
		
		
		// Freunde Verwaltung 
		
		friends.setItem(0, space);friends.setItem(1, space);friends.setItem(2, space);friends.setItem(3, space);friends.setItem(4, freundeV);friends.setItem(5, space);
		friends.setItem(6, space);friends.setItem(7, space);friends.setItem(8, space);friends.setItem(9, space);friends.setItem(17, space);friends.setItem(18, space);
		friends.setItem(26, space);friends.setItem(27, space);friends.setItem(35, space);friends.setItem(36, space);friends.setItem(37, space);friends.setItem(38, space);
		friends.setItem(39, null); //Vorherige Seite
		friends.setItem(40, null); //Aktuelle Seite | Seite 1/1
		friends.setItem(41, null); //Nächste Seite
		friends.setItem(42, space);friends.setItem(43, space);friends.setItem(44, space);
		friends.setItem(45, back);
		friends.setItem(46, null); //Freundschaftsanfragen(Anz)
		friends.setItem(47, null); //Freundschaftseinstellungen
		friends.setItem(48, space);friends.setItem(49, space);friends.setItem(50, space);
		friends.setItem(51, null); //Freund hinzufügen
		friends.setItem(52, null); //Sortieren
		friends.setItem(53, space);
		
		// Freunde Verwaltung
		
		
		// Einstellungen
		
		einst.setItem(0, space);einst.setItem(1, space);einst.setItem(2, space);einst.setItem(3, space);einst.setItem(4, setup);einst.setItem(5, space);
		einst.setItem(6, space);einst.setItem(7, space);einst.setItem(8, space);einst.setItem(9, space);einst.setItem(17, space);einst.setItem(18, space);
		einst.setItem(26, space);einst.setItem(27, back);einst.setItem(28, space);einst.setItem(29, space);einst.setItem(30, space);einst.setItem(31, space); //28 35
		einst.setItem(32, space);einst.setItem(33, space);einst.setItem(34, space);einst.setItem(35, space);
		
		// Einstellungen
		
		org.bukkit.inventory.ItemStack clicked = e.getCurrentItem();
		
		if(!Main.building.contains((Player)e.getWhoClicked())) {
			
			
			
			if(p.getItemInHand().getType().equals(Material.COMPASS)) {
				
				
			if(clicked != null &&clicked.getType().equals(Material.NETHER_STAR)) {
					World w = e.getWhoClicked().getLocation().getWorld();
					Location spawn = new Location(w,113.464,71,-143.337); //0.509,40,0.399 // w,113.464,71,-143.337
					
				
					if(!p.hasPermission("*")){
						p.setGameMode(GameMode.ADVENTURE);
					}
					
						
						p.setGameMode(GameMode.CREATIVE);
					
							
						
						
					
					
						
				p.teleport(new Location(w,113.464,71,-143.337)); //113 70 -144
			p.teleport(spawn);
			p.sendMessage("§6>> §eDarkException §6| §7Du wurdest zum §bSpawn §7teleportiert.");
		}	
				
					
			}else if(p.getItemInHand().getType().equals(Material.WATCH)) {
					if(clicked != null &&clicked.getItemMeta().getDisplayName().contains("Bauserver")){
						
						Bungee.connect(pl, p, "bauserver");
						e.getWhoClicked().closeInventory();
						
					}
					
					return;
					
			}  	else if(p.getItemInHand().getType().equals(Material.SKULL_ITEM)){
				if(clicked != null &&clicked.getType().equals(Material.BARRIER)){
					
					p.closeInventory();
					p.openInventory(meinprofil);
					
					
				}
					if( clicked != null && clicked.getType().equals(Material.BOOK)){
			
						p.openInventory(stats);
						e.setCancelled(true);
						
						
						
						return;
						
					}else if (clicked != null && clicked.getType().equals(Material.SKULL_ITEM)&& clicked.getItemMeta().getDisplayName().contains("Freunde-Verwaltung")){		
						p.openInventory(friends);
						
						
					}else if(clicked != null && clicked.getType().equals(Material.CAKE)){
						p.closeInventory();
						p.sendMessage("§6>> §eDarkException §6| §7Diese Funktion ist noch nicht Verfügbar.");
						//Inventory inv = Bukkit.createInventory(p, 9*4, "§e§l>> §7Party-Verwaltung");
						//p.openInventory(inv);
					}else if(clicked != null && clicked.getType().equals(Material.REDSTONE_COMPARATOR)){
						p.openInventory(einst);
					}
				
				
				
			}
		e.setCancelled(true);		
		}

		
		
		
		
	}
	
	@EventHandler
	public void onRightClick(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		
		ItemStack space = new ItemStack(Material.STAINED_GLASS_PANE,1,(short)7);
		ItemMeta spaceM = space.getItemMeta();
		spaceM.setDisplayName(" ");
		space.setItemMeta(spaceM);
		

		
			ItemStack kopf = new ItemStack(Material.SKULL_ITEM,1,(short)3);
			SkullMeta kopfM = (SkullMeta) kopf.getItemMeta();
			kopfM.setOwner(p.getName());
			kopfM.setDisplayName("§6§l>> §eFreunde-Verwaltung");
			kopfM.setLore(Arrays.asList("§b§nLinks- /Rechtsklick: §r§7Freunde-Verwaltung","§7anzeigen"));
			
		if(p.getItemInHand().getType().equals(Material.COMPASS)) {
			Inventory inv = Bukkit.createInventory(p, 9*3, "§e§l>> §7Navigation");
			
		
			
	
			
			ItemStack Spawn = new ItemStack(Material.NETHER_STAR);
			ItemMeta SpawnM = Spawn.getItemMeta();
			SpawnM.setDisplayName("§6§l>> §eSpawn");
			Spawn.setItemMeta(SpawnM);
			
			ItemStack Bed = new ItemStack(Material.BED);
			ItemMeta BedM = Bed.getItemMeta();
			BedM.setDisplayName("§6§l>> §eBedwars Duel");
			Bed.setItemMeta(BedM);
			
			ItemStack Unknow = new ItemStack(Material.SKULL_ITEM,1,(short)3);
			SkullMeta UnknowM = (SkullMeta) Unknow.getItemMeta();
			UnknowM.setOwner("MHF_Question");
			UnknowM.setDisplayName("§6§l>> §cBald Verfügbar!");
			Unknow.setItemMeta(UnknowM);
			
			inv.setItem(22, Spawn);
			inv.setItem(0, space); inv.setItem(1, space); inv.setItem(2, space); inv.setItem(3, space);inv.setItem(4, space);
			inv.setItem(5, space); inv.setItem(6, space); inv.setItem(7, space); inv.setItem(8, space);inv.setItem(9, space);
			inv.setItem(13, space); inv.setItem(17, space); inv.setItem(18, space); inv.setItem(19, space);inv.setItem(20, space);
			inv.setItem(21, space); inv.setItem(23, space); inv.setItem(24, space); inv.setItem(25, space); inv.setItem(26, space);
			inv.setItem(10, Bed); inv.setItem(11, Unknow); inv.setItem(12, Unknow);
			inv.setItem(14, Unknow);inv.setItem(15, Unknow);inv.setItem(16, Unknow);
			//22,23,24,25
			
			p.openInventory(inv);
			
		} else if(p.getItemInHand().getType().equals(Material.ENDER_PEARL)) {
			for(Player target : Bukkit.getOnlinePlayers()){
				p.hidePlayer(target);
		
				
			}
			
			ItemStack vanish = new ItemStack(Material.EYE_OF_ENDER);
			ItemMeta vanishM = vanish.getItemMeta();
			vanishM.setDisplayName("§a§oSpieler anzeigen §7§o<Rechtsklick>");
			vanish.setItemMeta(vanishM);
			
			p.getInventory().setItem(4, vanish);
			
		}else if (p.getItemInHand().getType().equals(Material.EYE_OF_ENDER)){
			for(Player target : Bukkit.getOnlinePlayers()){
				p.showPlayer(target);
				
				
		
				
			
			}
			
			ItemStack vanish = new ItemStack(Material.ENDER_PEARL);
			ItemMeta vanishM = vanish.getItemMeta();
			vanishM.setDisplayName("§c§oSpieler verstecken §7§o<Rechtsklick>");
			vanish.setItemMeta(vanishM);
			
			p.getInventory().setItem(4, vanish);
			
		}else if(p.getItemInHand().getType().equals(Material.WATCH)) {
			Inventory inv = Bukkit.createInventory(p, 9*1, "§e§l>> §7Server Auswahl");
			
			
			
			//String server = p.getServer().getServerName();
			
			//ItemMeta itemM = null;
			
			
				CustomDyeColor color = CustomDyeColor.LIME;
				byte customData = color.getData();
				ItemStack item1 = new ItemStack(Material.INK_SACK, 1, customData);
				ItemMeta itemM = item1.getItemMeta();
				itemM.setDisplayName("§a§lBauserver");
				itemM.setLore(Arrays.asList(" ","§c§oHier kannst du Vor-Bauen!"));
				item1.setItemMeta(itemM);
			
			
			
			inv.setItem(0, item1);
			
			p.openInventory(inv);
			
			
			
		} else if(p.getItemInHand().getType() == Material.SKULL_ITEM){
			Inventory meinprofil = Bukkit.createInventory(p, 9*3, "§e§l>> §cMein Profil");
			
			
			
			ItemStack stats = new ItemStack(Material.BOOK);
			ItemMeta statsM = stats.getItemMeta();
			statsM.setDisplayName("§6§l>> §eStatistiken");
			statsM.setLore(Arrays.asList("§b§nLinksklick: §r§7Deine Statistiken anzeigen","§b§nRechtsklick: §r§7Statistiken von einem","§7anderen Spieler anzeigen"));
			stats.setItemMeta(statsM);
			
			ItemStack party = new ItemStack(Material.CAKE);
			ItemMeta partyM = party.getItemMeta();
			partyM.setDisplayName("§6§l>> §eParty-Verwaltung");
			partyM.setLore(Arrays.asList("§b§nLinks- /Rechtsklick: §r§7Party-Verwaltung","§7anzeigen"));
			party.setItemMeta(partyM);
			
			ItemStack friends = new ItemStack(Material.SKULL_ITEM,1,(short)3);
			SkullMeta friendsM = (SkullMeta) friends.getItemMeta();
			friendsM.setOwner("ErebosTheDark");
			friendsM.setDisplayName("§6§l>> §eFreunde-Verwaltung");
			friendsM.setLore(Arrays.asList("§b§nLinks- /Rechtsklick: §r§7Freunde-Verwaltung","§7anzeigen"));
			friends.setItemMeta(friendsM);

			ItemStack settings = new ItemStack(Material.REDSTONE_COMPARATOR);
			ItemMeta settingsM = settings.getItemMeta();
			settingsM.setDisplayName("§6§l>> §eEinstellungen");
			settingsM.setLore(Arrays.asList("§b§nLinks- /Rechtsklick: §r§7Einstellungen","§7anzeigen"));
			settings.setItemMeta(settingsM);
			
			
			ItemStack profil = new ItemStack(Material.SKULL_ITEM,1,(short)3);
			SkullMeta profilM = (SkullMeta) profil.getItemMeta();
			profilM.setOwner(p.getName());
			profilM.setDisplayName("§6§l>> §c§l"+e.getPlayer().getName());
			
			if(p.hasPermission("dnetwork.admin")){
				
				profilM.setLore(Arrays.asList("§7----------------","§7Rang: §4Admin"));
				
			}else if(p.hasPermission("dnetwork.dev")){
				profilM.setLore(Arrays.asList("§7----------------","§7Rang: §9Developer"));
			}else if(p.hasPermission("dnetwork.mod")){
				profilM.setLore(Arrays.asList("§7----------------","§7Rang: §2Moderator"));
			}else {
				profilM.setLore(Arrays.asList("§7----------------","§7Rang: §aSpieler"));
			}
			
			profil.setItemMeta(profilM);
			
			meinprofil.setItem(0, space); meinprofil.setItem(1, space); meinprofil.setItem(2, space); meinprofil.setItem(3, space);
			meinprofil.setItem(4, profil);
			meinprofil.setItem(12, friends);
			meinprofil.setItem(14, party);
			meinprofil.setItem(10, stats);
			meinprofil.setItem(15, space);
			meinprofil.setItem(5, space);meinprofil.setItem(7, space); meinprofil.setItem(8, space);meinprofil.setItem(9, space);meinprofil.setItem(6, space);meinprofil.setItem(11, space);
			meinprofil.setItem(13, space); meinprofil.setItem(17, space); meinprofil.setItem(18, space); meinprofil.setItem(19, space);meinprofil.setItem(20, space);meinprofil.setItem(22, space);
			meinprofil.setItem(21, space); meinprofil.setItem(23, space); meinprofil.setItem(24, space); meinprofil.setItem(25, space); meinprofil.setItem(26, space);
			meinprofil.setItem(16, settings);
			p.openInventory(meinprofil);
			
			
			
			
			
		}else if(p.getItemInHand().getType().equals(Material.CHEST)){
			Inventory inv = Bukkit.createInventory(p, 9*6, "§e§l>> §7Extras");
			
			p.openInventory(inv);
			
			
		}else if(p.getItemInHand().getType() == null) {
			
			p.sendMessage("");
			
		}
		
		
		
	}

	public enum CustomDyeColor{

	    BLACK(0), //ink sack
	    RED(1),
	    GREEN(2),
	    BROWN(3),
	    BLUE(4), //lapis lazuli
	    PURPLE(5),
	    CYAN(6),
	    LIGHT_GRAY(7),
	    GRAY(8),
	    PINK(9),
	    LIME(10),
	    YELLOW(11),
	    LIGHT_BLUE(12),
	    MAGENTA(13),
	    ORANGE(14),
	    WHITE(15); //bonemeal

	    private byte data;
	    CustomDyeColor(int i){
	      this.data = (byte) i;
	    }

	    public byte getData(){
	      return this.data;
	    }
	}
	
}

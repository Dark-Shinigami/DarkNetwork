package de.psych.main.events;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import de.psych.main.Main;
import de.psych.main.utils.BanManager;

public class JoinListener implements Listener {

	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		if(BanManager.isBanned(p.getUniqueId().toString())){
			BanManager.unban(p.getUniqueId().toString());
		}
	}
	
	
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		
		
		
		
		Player p = e.getPlayer();
		
		
		 @SuppressWarnings("unused")
		World world = Bukkit.createWorld(new WorldCreator("limbo"));
		p.teleport(Bukkit.getWorld("limbo").getSpawnLocation());
		
		ScoreboardEvent.setBoard(p);
		
		p.setGameMode(GameMode.ADVENTURE);
		
		
		
		
		ItemStack Compass = new ItemStack(Material.COMPASS);
		ItemMeta CompassM = Compass.getItemMeta();
		CompassM.setDisplayName("§9§lNavigation §7§o<Rechtsklick>");
		Compass.setItemMeta(CompassM);
		
		ItemStack profil = new ItemStack(Material.SKULL_ITEM,1,(short)3);
		SkullMeta profilM = (SkullMeta) profil.getItemMeta();
		profilM.setOwner(p.getName());
		profilM.setDisplayName("§9§lMein Profil §7§o<Rechtsklick>");
		profil.setItemMeta(profilM);
		
		ItemStack lobby = new ItemStack(Material.WATCH);
		ItemMeta lobbyM = lobby.getItemMeta();
		lobbyM.setDisplayName("§9§lServer-Auswahl §7§o<Rechtsklick>");
		lobby.setItemMeta(lobbyM);
		
		ItemStack extras = new ItemStack(Material.CHEST);
		ItemMeta extrasM = extras.getItemMeta();
		extrasM.setDisplayName("§9§lExtras §7§o<Rechtsklick>");
		extras.setItemMeta(extrasM);
		
		
		p.getInventory().setItem(1, profil);
		p.getInventory().setItem(0, Compass);
		p.getInventory().setItem(8, lobby);
		p.getInventory().setItem(7, extras);
		
		World w = p.getWorld();
		Location spawn = new Location(w,113.5,70,143.5); //113 70 -144 //0.509,40,0.399
		p.teleport(spawn);
		
		return;
		}
	@EventHandler
	public void onLeave(PlayerQuitEvent e){
		Player p = e.getPlayer();
		Main.login.remove(p.getName());
	}
		
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		if(Main.building.contains(p)){
			e.setCancelled(false);
		}else{
		e.setCancelled(true);	
		}
		
	}  
	
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		if(Main.building.contains(p)){
			e.setCancelled(false);
		}else{
		e.setCancelled(true);	
		}
		
	}  
	@EventHandler(priority = EventPriority.MONITOR)
	public void onThrow(PlayerInteractEvent e){
	
		if(e.getPlayer().getItemInHand().equals(Material.ENDER_PEARL)){
			
		if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
			e.setCancelled(true);
			
		}	
			
			
		}
		
	}
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
	Player player = e.getPlayer();
	if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
	if(player.getItemInHand().getType().equals(Material.ENDER_PEARL) || player.getItemInHand().getType().equals(Material.EYE_OF_ENDER)) {
	e.setCancelled(true);
	        }
	    }
	}

	@EventHandler
	public void onPickup(PlayerPickupItemEvent e){
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onFood(FoodLevelChangeEvent e){
		e.setCancelled(true);
	}
	@EventHandler
	public void onProjectileLaunch(ProjectileLaunchEvent e){
		e.setCancelled(true);
	}
	@EventHandler
	public void BlockPhsyic(BlockPhysicsEvent e){
		e.setCancelled(true);
	}
	@EventHandler
	public void onWeather(WeatherChangeEvent e){
		e.setCancelled(true);
	}
  
	@EventHandler
	public void noUproot(PlayerInteractEvent event)
	{
	    if(event.getAction() == Action.PHYSICAL && event.getClickedBlock().getType() == Material.SOIL)
	        event.setCancelled(true);
	}
}

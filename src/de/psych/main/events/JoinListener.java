package de.psych.main.events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.psych.main.Main;

public class JoinListener implements Listener{

	
	
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		
		
		
		Player p = e.getPlayer();
		
		ItemStack Compass = new ItemStack(Material.COMPASS);
		ItemMeta CompassM = Compass.getItemMeta();
		CompassM.setDisplayName("§a§oGame Selection");
		Compass.setItemMeta(CompassM);
		
		ItemStack vanish = new ItemStack(Material.ENDER_PEARL);
		ItemMeta vanishM = vanish.getItemMeta();
		vanishM.setDisplayName("§c§oHide Players");
		vanish.setItemMeta(vanishM);
		
		
		ItemStack lobby = new ItemStack(Material.REDSTONE);
		ItemMeta lobbyM = lobby.getItemMeta();
		lobbyM.setDisplayName("§a§oSwitch Lobby");
		lobby.setItemMeta(lobbyM);
		
		
		p.getInventory().setItem(4, vanish);
		p.getInventory().setItem(0, Compass);
		p.getInventory().setItem(8, lobby);
		
		World w = p.getWorld();
		Location spawn = new Location(w,-395.834,63.00000,124.481);
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
	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.MONITOR)
	public void onThrow(PlayerInteractEvent e){
	
		if(e.getPlayer().getItemInHand().equals(Material.ENDER_PEARL)){
			
		if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
			e.setCancelled(true);
			
		}	
			
			
		}
		
	}
	@SuppressWarnings("deprecation")
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
	public void onDMG(EntityDamageEvent e) {
		e.setCancelled(true);
	}


}

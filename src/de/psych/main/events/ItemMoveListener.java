package de.psych.main.events;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import de.psych.main.Main;
import de.psych.main.utils.Bungee;

public class ItemMoveListener implements Listener {
	
	Main pl;
	
	public ItemMoveListener(Main main){
		this.pl = main;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onItemMove(InventoryClickEvent e) {
		org.bukkit.inventory.ItemStack clicked = e.getCurrentItem();
		
		if(!Main.building.contains((Player)e.getWhoClicked())) {
			
			Player p = (Player)e.getWhoClicked();
			
			if(p.getItemInHand().getType().equals(Material.COMPASS)) {
				if(clicked.getType().equals(Material.NETHER_STAR)) {
					World w = e.getWhoClicked().getLocation().getWorld();
					Location spawn = new Location(w,-395.834,63.00000,124.481);
					
					if(p.isOp()){
						p.setGameMode(GameMode.CREATIVE);
					} else {
						p.setGameMode(GameMode.ADVENTURE);
						
					}
					
					
				p.teleport(new Location(w,-395.834,63.00000,124.481));
			p.teleport(spawn);
			p.sendMessage("§7Teleported to §3§oSpawn");
		}	
				else 
						if(clicked.getType().equals(Material.IRON_PICKAXE)) {
			
					
					p.setGameMode(GameMode.SURVIVAL);
				//	Bungee.connect(pl, p, "survival");
				//	p.sendMessage("§7Teleported to §a§oSurvival");
					p.sendMessage("§cWork in Progress...");
				
				
	
		
		
		}
				
					
			}else if(p.getItemInHand().getType().equals(Material.REDSTONE)) {
				if(clicked.getType().equals(Material.GLOWSTONE_DUST)) {
					if(clicked.getItemMeta().getDisplayName().contains("Lobby 1")){
						
						// Bungee.connect(pl, p, "lobby1");
						p.sendMessage("§cWork in Progress...");
					}else if(clicked.getItemMeta().getDisplayName().contains("Lobby 2")) {
						// Bungee.connect(pl, p, "lobby2");
						p.sendMessage("§cWork in Progress...");
					}
					
				}
			}  	
		e.setCancelled(true);		
		}

		
		
		
		
	}
	
	
	
}

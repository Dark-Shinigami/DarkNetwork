package de.psych.main.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RightClickListener implements Listener {
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onRightClick(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		
		if(p.getItemInHand().getType().equals(Material.COMPASS)) {
			Inventory inv = Bukkit.createInventory(p, 9*4, "              §6Selection");
			
		
			
			ItemStack spawn = new ItemStack(Material.NETHER_STAR);
			ItemMeta spawnM = spawn.getItemMeta();
			spawnM.setDisplayName("§3§oSpawn");
			spawn.setItemMeta(spawnM);
			
			ItemStack Sky = new ItemStack(Material.IRON_PICKAXE);
			ItemMeta SkyM = spawn.getItemMeta();
			SkyM.setDisplayName("§a§oSurvival");
			Sky.setItemMeta(SkyM);
			
			inv.setItem(0, Sky);
			inv.setItem(13, spawn);
			
			p.openInventory(inv);
			
		} else if(p.getItemInHand().getType().equals(Material.ENDER_PEARL)) {
			for(Player target : Bukkit.getOnlinePlayers()){
				p.hidePlayer(target);
		
				
			}
			
			ItemStack vanish = new ItemStack(Material.EYE_OF_ENDER);
			ItemMeta vanishM = vanish.getItemMeta();
			vanishM.setDisplayName("§a§oShow Players");
			vanish.setItemMeta(vanishM);
			
			p.getInventory().setItem(4, vanish);
			
		}else if (p.getItemInHand().getType().equals(Material.EYE_OF_ENDER)){
			for(Player target : Bukkit.getOnlinePlayers()){
				p.showPlayer(target);
				
				
		
				
			
			}
			
			ItemStack vanish = new ItemStack(Material.ENDER_PEARL);
			ItemMeta vanishM = vanish.getItemMeta();
			vanishM.setDisplayName("§c§oHide Players");
			vanish.setItemMeta(vanishM);
			
			p.getInventory().setItem(4, vanish);
			
		}else if(p.getItemInHand().getType().equals(Material.REDSTONE)) {
			Inventory inv = Bukkit.createInventory(p, 9*1, "           §6Lobby Selection");
			
			
			
			String server = p.getServer().getServerName();
			
			ItemStack lobby1 = null;
			ItemStack lobby2 = null;
			
			ItemMeta lobby1M = null;
			ItemMeta lobby2M = null;
			
			
			switch(server){
			case "lobby1":
				
				 lobby1 = new ItemStack(Material.BLAZE_POWDER);
				 lobby1M = lobby1.getItemMeta();
				lobby1M.setDisplayName("§6§l§oLobby 1 §7§o(Current Lobby)");
				lobby1.setItemMeta(lobby1M);
				
				 lobby2 = new ItemStack(Material.GLOWSTONE_DUST);
				 lobby2M = lobby1.getItemMeta();
				lobby2M.setDisplayName("§3§l§oLobby 2");
				lobby2.setItemMeta(lobby2M);
				break;
			case "lobby2":
				 lobby1 = new ItemStack(Material.GLOWSTONE_DUST);
				 lobby1M = lobby1.getItemMeta();
				lobby1M.setDisplayName("§3§l§oLobby 1");
				lobby1.setItemMeta(lobby1M);
				
				 lobby2 = new ItemStack(Material.BLAZE_POWDER);
				 lobby2M = lobby1.getItemMeta();
				lobby2M.setDisplayName("§6§l§oLobby 2 §7§o(Current Lobby)");
				lobby2.setItemMeta(lobby2M);
				break;
			default:
				lobby1 = new ItemStack(Material.GLOWSTONE_DUST);
				 lobby1M = lobby1.getItemMeta();
				lobby1M.setDisplayName("§3§l§oLobby 1");
				lobby1.setItemMeta(lobby1M);
				
				 lobby2 = new ItemStack(Material.GLOWSTONE_DUST);
				 lobby2M = lobby1.getItemMeta();
				lobby2M.setDisplayName("§3§l§oLobby 2");
				lobby2.setItemMeta(lobby2M);	
				
				
			}
			
			
			inv.setItem(0, lobby1);
			inv.setItem(2, lobby2);
			
			p.openInventory(inv);
			
		}
		
	}

	
}

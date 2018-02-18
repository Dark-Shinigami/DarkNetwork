package de.psych.main.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

import de.psych.main.utils.BanManager;

public class PlayerLogin implements Listener {
	

	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent e){
		Player p = e.getPlayer();
		if(BanManager.isBanned(p.getUniqueId().toString())){
			long current = System.currentTimeMillis();
			long end = BanManager.getEnd(p.getUniqueId().toString());
			if(current < end | end == -1){
				e.disallow(Result.KICK_BANNED, "§cDu wurdest vom Server gebannt!\n" + 
						   	"\n" + 
							"§3Grund: §e" + BanManager.getReason(p.getUniqueId().toString()) + "\n" +
						    "\n" +
						    "§3Verbleibende Zeit: §e" + BanManager.getRemainingTime(p.getUniqueId().toString()) +"\n" +
							"§3Du kannst §c§nkeinen§r §3Entbannugsantrag stellen!");
			}
		}
		
	}

}
